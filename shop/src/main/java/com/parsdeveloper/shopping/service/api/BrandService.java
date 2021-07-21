package com.parsdeveloper.shopping.service.api;

import com.parsdeveloper.shopping.model.dto.BrandDto;
import com.parsdeveloper.shopping.model.entity.shop.Brand;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BrandService {

    List<BrandDto> fetchAllBrands();

    Brand save(BrandDto brandDto);
    List<BrandDto> getBrandByCategoryAndCompany(Long categoryId,Long companyId);
}
