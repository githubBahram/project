package com.parsdeveloper.shopping.controller;

import com.parsdeveloper.shopping.model.dto.CategoryDto;
import com.parsdeveloper.shopping.model.entity.shop.Category;
import com.parsdeveloper.shopping.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/categoryManager")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/categories")
    protected ResponseEntity findAll(Pageable pageable) {
        Page<Category> all = categoryService.findAll(pageable);
        return ResponseEntity.ok(all);
    }

    @PostMapping("/category")
    public ResponseEntity<Category> save(CategoryDto categoryDto) throws IOException {
        Category category = categoryService.save(categoryDto);
        return ResponseEntity.ok(category);
    }

    @GetMapping("/categories/isRoot/companies/{companyId}")
    public ResponseEntity<List<CategoryDto>> getRootCategories(@PathVariable("companyId") Long companyId) {
        List<CategoryDto> categoryDtoList = categoryService.findRootCategories(companyId);
        return ResponseEntity.ok(categoryDtoList);
    }

    @GetMapping("/categories/{categoryId}")
    public ResponseEntity<CategoryDto> getChildesCategory(@PathVariable("categoryId") Long categoryId) {
        CategoryDto categoryDtoList = categoryService.findChildesCategory(categoryId);
        return ResponseEntity.ok(categoryDtoList);
    }

//    @GetMapping("/categories/{str}")
//    public ResponseEntity<List<Category>> getStrCategories(@RequestParam("companyId") Long companyId) {
//        List<Category> categoryDtoList = new ArrayList<>();// categoryService.findRootCategories(categoryId);
//        return ResponseEntity.ok(categoryDtoList);
//    }
}
