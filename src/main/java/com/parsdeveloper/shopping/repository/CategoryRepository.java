package com.parsdeveloper.shopping.repository;

import com.parsdeveloper.shopping.model.entity.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long > {
}
