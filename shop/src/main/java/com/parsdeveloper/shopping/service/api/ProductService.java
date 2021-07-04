package com.parsdeveloper.shopping.service.api;

import com.parsdeveloper.shopping.model.dto.ProductDto;
import com.parsdeveloper.shopping.model.entity.shop.Product;
import com.parsdeveloper.shopping.model.filter.ProductFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);

    Product save(ProductDto productDto) throws IOException;

    @Transactional
    Page<ProductDto> findProductByCategory(ProductFilter productFilter);

    @Transactional
    Page<ProductDto> findProductByCompanyAndCategory(Long companyId, Long categoryId, ProductFilter productFilter);
}
