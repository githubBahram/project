package com.parsdeveloper.shopping.model.commons.annotations.filter;

@FunctionalInterface
@Deprecated
public interface DtoMapper<T, DTO> {
    @Deprecated
    DTO map(T object);
}
