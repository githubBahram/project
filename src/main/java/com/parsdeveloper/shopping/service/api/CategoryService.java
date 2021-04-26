package com.parsdeveloper.shopping.service.api;

import com.parsdeveloper.shopping.model.dto.CategoryDto;
import com.parsdeveloper.shopping.model.entity.shop.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CategoryService {

    Page<Category> findAll(Pageable pageable);

    Category save(MultipartFile image,CategoryDto categoryDto) throws IOException;
}
