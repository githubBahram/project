package com.parsdeveloper.shopping.service.api;

import com.parsdeveloper.shopping.model.dto.CategoryDTO;
import com.parsdeveloper.shopping.model.entity.shop.Category;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findAll();

    Category save(CategoryDTO categoryDTO) throws IOException;
}
