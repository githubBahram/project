package com.parsdeveloper.shopping.service.impl;

import com.parsdeveloper.shopping.model.dto.CategoryDto;
import com.parsdeveloper.shopping.model.entity.shop.Category;
import com.parsdeveloper.shopping.model.entity.shop.CategoryImage;
import com.parsdeveloper.shopping.repository.CategoryImageRepository;
import com.parsdeveloper.shopping.repository.CategoryRepository;
import com.parsdeveloper.shopping.service.api.AWSS3Service;
import com.parsdeveloper.shopping.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class DefaultCategoryService implements CategoryService {

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    CategoryImageRepository categoryImageRepository;
    @Autowired
    private AWSS3Service awss3Service;

    @Override
    @Transactional
    public Page<Category> findAll(Pageable pageable) {
        return categoryRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Category save(CategoryDto categoryDto) throws IOException {

        Optional<Category> root=categoryRepository.findById(categoryDto.getRootId()==null?-1L:categoryDto.getRootId());
        Optional<Category> parent=categoryRepository.findById(categoryDto.getParentId()==null?-1L:categoryDto.getParentId());

        String imageName=awss3Service.uploadFile(categoryDto.getImage());

        CategoryImage categoryImage=new CategoryImage();
        categoryImage.setLocation(bucketName);
        categoryImage.setName(imageName);
        categoryImage=categoryImageRepository.save(categoryImage);

        Category category = new Category();
        category.setRoot(root.orElse(null));
        category.setParent(parent.orElse(null));
        category.setImage(categoryImage);
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        category=categoryRepository.save(category);

        if(category.getRoot()==null){
            category.setRoot(category);
        }
        if (category.getParent()==null){
            category.setParent(category);
        }

        return categoryRepository.save(category);
    }
}
