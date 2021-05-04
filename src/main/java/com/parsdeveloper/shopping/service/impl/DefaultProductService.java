package com.parsdeveloper.shopping.service.impl;

import com.parsdeveloper.shopping.model.dto.ProductDto;
import com.parsdeveloper.shopping.model.entity.shop.Category;
import com.parsdeveloper.shopping.model.entity.shop.Product;
import com.parsdeveloper.shopping.model.entity.shop.ProductImage;
import com.parsdeveloper.shopping.repository.CategoryRepository;
import com.parsdeveloper.shopping.repository.ProductImageRepository;
import com.parsdeveloper.shopping.repository.ProductRepository;
import com.parsdeveloper.shopping.service.api.AWSS3Service;
import com.parsdeveloper.shopping.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Optional;

@Service
public class DefaultProductService implements ProductService {

    @Value("${aws.s3.bucket}")
    private String bucketName;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductImageRepository productImageRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private AWSS3Service awss3Service;

    @Override
    @Transactional
    public Page<Product> findAll(Pageable pageable) {

        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Product save(ProductDto productDto) throws IOException {


        Optional<Category> category=categoryRepository.findById(productDto.getCategoryId());

        String imageName=awss3Service.uploadFile(productDto.getImage());

        ProductImage productImage=new ProductImage();
        productImage.setLocation(bucketName);
        productImage.setName(imageName);
        productImage=productImageRepository.save(productImage);

        Product product = new Product();
        product.setCategory(category.get());
        product.setImage(productImage);
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product=productRepository.save(product);

        return product;
    }
}
