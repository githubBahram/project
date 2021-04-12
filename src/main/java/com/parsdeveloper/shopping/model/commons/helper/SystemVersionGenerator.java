package com.parsdeveloper.shopping.model.commons.helper;

import com.parsdeveloper.shopping.model.entity.security.EffectiveVersionModel;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;

public class SystemVersionGenerator implements ValueGenerator<Long> {
    @Override
    public Long generateValue(Session session, Object owner) {
        if (owner instanceof EffectiveVersionModel) {
            Long currentVersionModel = ((EffectiveVersionModel) owner).getServiceVersion();
            if (currentVersionModel == null)
                return 1L;
            else return ++currentVersionModel;
        }
        return null;
    }
}
