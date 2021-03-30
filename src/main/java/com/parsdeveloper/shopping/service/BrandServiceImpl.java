package com.parsdeveloper.shopping.service;

import com.parsdeveloper.shopping.model.dto.BrandDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Override
    public List<BrandDTO> fetchAllBrands() {
        List<BrandDTO> brandDTOList = new ArrayList<>();
        brandDTOList.add(new BrandDTO(1L,"kala",""));
        brandDTOList.add(new BrandDTO(2L,"telavang",""));
        brandDTOList.add(new BrandDTO(3L,"tabarok",""));
        brandDTOList.add(new BrandDTO(4L,"mana",""));
        return brandDTOList;
    }

}
