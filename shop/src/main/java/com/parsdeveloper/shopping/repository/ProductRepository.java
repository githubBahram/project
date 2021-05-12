package com.parsdeveloper.shopping.repository;

import com.parsdeveloper.shopping.model.entity.shop.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductRepository extends ApplicationRepository<Product> {

    Page<Product> findAll(Pageable pageable);

}
