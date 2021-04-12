package com.parsdeveloper.shopping.service.api;

import com.parsdeveloper.shopping.model.dto.BrandDTO;

import java.util.List;

public interface BrandService {
    List<BrandDTO> fetchAllBrands();
}
