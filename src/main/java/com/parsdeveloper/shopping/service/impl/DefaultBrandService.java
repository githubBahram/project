package com.parsdeveloper.shopping.service.impl;

import com.parsdeveloper.shopping.model.dto.BrandDto;
import com.parsdeveloper.shopping.service.api.BrandService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DefaultBrandService implements BrandService {

    @Override
    public List<BrandDto> fetchAllBrands() {
        List<BrandDto> brandDTOList = new ArrayList<>();
        brandDTOList.add(new BrandDto(1L,"kala",""));
        brandDTOList.add(new BrandDto(2L,"telavang",""));
        brandDTOList.add(new BrandDto(3L,"tabarok",""));
        brandDTOList.add(new BrandDto(4L,"mana",""));
        return brandDTOList;
    }

}
