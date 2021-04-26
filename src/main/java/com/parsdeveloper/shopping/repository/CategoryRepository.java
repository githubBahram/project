package com.parsdeveloper.shopping.repository;

import com.parsdeveloper.shopping.model.entity.shop.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryRepository extends ApplicationRepository<Category> {

    Page<Category> findAll(Pageable pageable);
}
