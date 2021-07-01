package com.parsdeveloper.shopping.service.api;

import com.parsdeveloper.shopping.model.dto.ProductDto;
import com.parsdeveloper.shopping.model.entity.shop.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.IOException;
import java.util.List;

public interface ProductService {

    Page<Product> findAll(Pageable pageable);

    Product save(ProductDto productDto) throws IOException;
    Page<ProductDto> findProductByCompanyAndCategory(Long companyId,Long CategoryId,Pageable pageable);
}
