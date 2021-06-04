package com.parsdeveloper.shopping.service.impl;

import com.parsdeveloper.shopping.model.dto.BrandDto;
import com.parsdeveloper.shopping.model.entity.shop.Brand;
import com.parsdeveloper.shopping.model.entity.shop.BrandImage;
import com.parsdeveloper.shopping.repository.BrandImageRepository;
import com.parsdeveloper.shopping.repository.BrandRepository;
import com.parsdeveloper.shopping.service.api.AWSS3Service;
import com.parsdeveloper.shopping.service.api.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DefaultBrandService implements BrandService {

//    @Value("${aws.s3.bucket}")
//    private String bucketName;

    @Autowired
    private BrandRepository brandRepository;
    @Autowired
    private BrandImageRepository brandImageRepository;
    @Autowired
    private AWSS3Service awss3Service;

    @Override
    public List<BrandDto> fetchAllBrands() {
        List<BrandDto> brandDTOList = new ArrayList<>();
        return brandDTOList;
    }

    @Override
    @Transactional
    public Brand save(BrandDto brandDto) {

        String imageName=awss3Service.uploadFile(brandDto.getImage(),"image-product");

        BrandImage brandImage=new BrandImage();
        brandImage.setLocation("image-product");
        brandImage.setName(imageName);
        brandImage=brandImageRepository.save(brandImage);

        Brand brand=new Brand();
        brand.setName(brandDto.getName());
        brand.setImage(brandImage);
        return brandRepository.save(brand);
    }

}
