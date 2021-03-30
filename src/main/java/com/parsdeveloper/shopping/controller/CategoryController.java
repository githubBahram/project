package com.parsdeveloper.shopping.controller;

import com.parsdeveloper.shopping.model.dto.CategoryDTO;
import com.parsdeveloper.shopping.repository.ImageRepository;
import com.parsdeveloper.shopping.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("category-management")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ImageRepository imageRepository;

    @GetMapping(value = "/categories", produces = "application/json")
    public List<CategoryDTO> categoryDTOList() {
        return categoryService.findAll();
    }

    @PostMapping(value = "/category")
    public ResponseEntity<String> save(@RequestBody MultipartFile image,
                                         String name) {
        try {
            CategoryDTO categoryDTO=new CategoryDTO(name,image.getBytes(), image.getOriginalFilename());
            categoryService.save(categoryDTO);
        } catch (IOException e) {
            //todo log for exception
        }

        return ResponseEntity.ok("success");
    }
}
