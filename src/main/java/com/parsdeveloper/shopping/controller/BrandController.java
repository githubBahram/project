package com.parsdeveloper.shopping.controller;

import com.parsdeveloper.shopping.model.dto.BrandDto;
import com.parsdeveloper.shopping.service.api.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("brand-management")
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping(value = "/brands", produces = "application/json")
    public List<BrandDto> brandDTOList() {
        return brandService.fetchAllBrands();
    }
}
