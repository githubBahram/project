package com.parsdeveloper.shopping.repository;

import com.parsdeveloper.shopping.model.dto.ProductDto;
import com.parsdeveloper.shopping.model.filter.ProductFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductRepositoryCustom {
    Page<ProductDto> finaProduct(ProductFilter productFilter);
}
