package com.parsdeveloper.shopping.controller;

import com.parsdeveloper.shopping.model.dto.BrandDto;
import com.parsdeveloper.shopping.model.entity.shop.Brand;
import com.parsdeveloper.shopping.service.api.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("brands")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping
    public List<BrandDto> findAll() {
        return brandService.fetchAllBrands();
    }

    @PostMapping
    public ResponseEntity<Brand> save(BrandDto brandDto){
        Brand brand= brandService.save(brandDto);
        return ResponseEntity.ok(brand);
    }
}
