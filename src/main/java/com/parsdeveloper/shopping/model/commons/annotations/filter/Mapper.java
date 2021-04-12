package com.parsdeveloper.shopping.model.commons.annotations.filter;

@FunctionalInterface
public interface Mapper<PARAM, RESULT> {

    RESULT map(PARAM input);
}
