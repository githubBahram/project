package com.parsdeveloper.shopping.controller;

import com.parsdeveloper.shopping.model.dto.CategoryDto;
import com.parsdeveloper.shopping.model.entity.shop.Category;
import com.parsdeveloper.shopping.service.api.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping
    protected ResponseEntity findAll(Pageable pageable) {
        Page<Category> all = categoryService.findAll(pageable);
        return ResponseEntity.ok(all);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<String> save(@RequestBody MultipartFile image, CategoryDto categoryDto) throws IOException {
        categoryService.save(image,categoryDto);
        return ResponseEntity.ok("success");
    }
}
