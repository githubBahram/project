package com.parsdeveloper.shopping.model.commons.annotations.mapper;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
public @interface Mapper {
    String value();

    boolean defaultMapper() default false;

    String[] includeMappers() default {};
}
