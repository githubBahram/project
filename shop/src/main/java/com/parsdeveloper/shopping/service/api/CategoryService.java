package com.parsdeveloper.shopping.service.api;

import com.parsdeveloper.shopping.model.dto.CategoryDto;
import com.parsdeveloper.shopping.model.entity.shop.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface CategoryService {

    Page<Category> findAll(Pageable pageable);

    Category save(CategoryDto categoryDto) throws IOException;

    List<CategoryDto> findRootCategories(Long companyId);

    CategoryDto findChildesCategory(Long categoryId);
}
