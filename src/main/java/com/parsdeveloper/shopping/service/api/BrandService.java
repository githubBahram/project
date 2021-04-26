package com.parsdeveloper.shopping.service.api;

import com.parsdeveloper.shopping.model.dto.BrandDto;

import java.util.List;

public interface BrandService {
    List<BrandDto> fetchAllBrands();
}
