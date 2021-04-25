package com.parsdeveloper.shopping.service.impl;

import com.parsdeveloper.shopping.model.dto.CategoryDTO;
import com.parsdeveloper.shopping.model.entity.shop.CategoryImage;
import com.parsdeveloper.shopping.model.entity.shop.ProductImage;
import com.parsdeveloper.shopping.model.entity.shop.Category;
import com.parsdeveloper.shopping.repository.CategoryRepository;
import com.parsdeveloper.shopping.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultCategoryService implements CategoryService {

    @Autowired
    DefaultFileLocationService fileLocationService;

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    @Transactional
    public List<CategoryDTO> findAll() {
        List<Category> categoryList = (List<Category>) categoryRepository.findAll();
        List<CategoryDTO> categoryDTOList = new ArrayList<>();
        categoryList.forEach(category -> categoryDTOList.add(new CategoryDTO().map(category)));
        return categoryDTOList;
    }

    @Override
    @Transactional
    public Category save(CategoryDTO categoryDTO) throws IOException {
        CategoryImage image = new CategoryImage();
        Category category = new Category();
        category.setImage(image);
        category.setName(categoryDTO.getName());

        return categoryRepository.save(category);
    }
}
