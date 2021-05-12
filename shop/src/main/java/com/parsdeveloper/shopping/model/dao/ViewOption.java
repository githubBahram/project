package com.parsdeveloper.shopping.model.dao;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
public @interface ViewOption {

    String displayProperty() default "";

    String label() default "";

    boolean excludeView() default false;

    byte priority() default Byte.MAX_VALUE;

    boolean required() default true;

    DisplayMode displayMode() default DisplayMode.byType;

    boolean listViewEagerFetch() default false;

    //TODO
    boolean preLoadListOfValue() default false;

    /**
     * Specify the Condition for querying a preload LOV item
     * <p>example
     *
     * @ ViewOption ( preLoadLovCondition = "e.active = true")
     * use e as alias for entity
     */
    String preLoadLovCondition() default "";
}
