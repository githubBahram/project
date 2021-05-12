package com.parsdeveloper.shopping.model.commons.annotations.filter;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface EntityMapper {

    Class value();

    /**
     * just index 0 will be used
     */
    Class<? extends DtoMapper>[] dtoMapper() default {};
}
