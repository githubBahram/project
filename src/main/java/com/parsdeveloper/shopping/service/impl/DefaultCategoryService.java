package com.parsdeveloper.shopping.service.impl;

import com.parsdeveloper.shopping.model.dto.CategoryDto;
import com.parsdeveloper.shopping.model.entity.shop.Category;
import com.parsdeveloper.shopping.model.entity.shop.CategoryImage;
import com.parsdeveloper.shopping.repository.CategoryRepository;
import com.parsdeveloper.shopping.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class DefaultCategoryService implements CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Override
    @Transactional
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Category save(MultipartFile image,CategoryDto categoryDto) throws IOException {

//        CategoryImage categoryImage = saveCategoryImage();
        Optional<Category> root=categoryRepository.findById(categoryDto.getRootId());
        Optional<Category> parent=categoryRepository.findById(categoryDto.getParentId());

        Category category = new Category();
//        category.setImage(categoryImage);
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category=categoryRepository.save(category);

        if(category.getRoot()==null){
            category.setRoot(root.get());
        }
        if (category.getParent()==null){
            category.setParent(parent.get());
        }

        return categoryRepository.save(category);
    }
}
