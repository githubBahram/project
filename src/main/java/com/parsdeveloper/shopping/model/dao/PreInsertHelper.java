package com.parsdeveloper.shopping.model.dao;

import org.hibernate.event.spi.PreInsertEvent;

import javax.persistence.ManyToOne;
import java.lang.reflect.Method;

public class PreInsertHelper {

    public static boolean onPreInsert(PreInsertEvent event) {
        BaseModel entity = (BaseModel) event.getEntity();
        Class<? extends BaseModel> clazz = entity.getClass();
        Method[] clazzMethods = clazz.getDeclaredMethods();
        for (Method method : clazzMethods) {
            ManyToOne manyToOne = method.getAnnotation(ManyToOne.class);
            if (manyToOne != null) {
                try {
                    BaseModel obj = (BaseModel) method.invoke(entity);
                    if (obj != null && (obj.getId()).longValue() == -1) {
                        String methodName = method.getName();
                        methodName = methodName.replaceFirst("g", "s");
                        clazz.getMethod(methodName, method.getReturnType()).invoke(entity, new Object[]{null});
                    }
                } catch (Exception e) {
                    return Boolean.TRUE;
                }
            }
        }
        return Boolean.FALSE;
    }
}

