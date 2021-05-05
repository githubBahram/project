package com.parsdeveloper.shopping.controller;

import com.parsdeveloper.shopping.model.dto.BrandDto;
import com.parsdeveloper.shopping.model.entity.shop.Brand;
import com.parsdeveloper.shopping.service.api.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/brandManager")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping("/brands")
    public List<BrandDto> findAll() {
        return brandService.fetchAllBrands();
    }

    @PostMapping("/brand")
    public ResponseEntity<Brand> save(BrandDto brandDto){
        Brand brand= brandService.save(brandDto);
        return ResponseEntity.ok(brand);
    }
}
