package com.parsdeveloper.shopping.service.impl;

import com.parsdeveloper.shopping.model.dto.ProductDto;
import com.parsdeveloper.shopping.model.entity.shop.Category;
import com.parsdeveloper.shopping.model.entity.shop.Product;
import com.parsdeveloper.shopping.model.entity.shop.ProductImage;
import com.parsdeveloper.shopping.model.entity.shop.ProductPricing;
import com.parsdeveloper.shopping.model.filter.ProductFilter;
import com.parsdeveloper.shopping.model.filter.ProductSpec;
import com.parsdeveloper.shopping.repository.CategoryRepository;
import com.parsdeveloper.shopping.repository.ProductImageRepository;
import com.parsdeveloper.shopping.repository.ProductPricingRepository;
import com.parsdeveloper.shopping.repository.ProductRepository;
import com.parsdeveloper.shopping.service.api.AWSS3Service;
import com.parsdeveloper.shopping.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DefaultProductService implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductPricingRepository productPricingRepository;
    @Autowired
    ProductImageRepository productImageRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    private AWSS3Service awss3Service;

    @Value("${application.max.page.size}")
    Integer maxPageSize;

    @Override
    @Transactional
    public Page<Product> findAll(Pageable pageable) {

        return productRepository.findAll(pageable);
    }

    @Override
    @Transactional
    public Product save(ProductDto productDto) throws IOException {

        Optional<Category> category = categoryRepository.findById(productDto.getCategoryId());

        String imageName = awss3Service.uploadFile(productDto.getImage(), "image-product");

        ProductImage productImage = new ProductImage();
        productImage.setLocation("image-product");
        productImage.setName(imageName);
        productImage = productImageRepository.save(productImage);

        Product product = new Product();
        product.setCategory(category.get());
//        product.setImage(productImage);
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product = productRepository.save(product);

        return product;
    }

    @Override
    @Transactional
    public Page<ProductDto> findProductByCategory(ProductFilter productFilter) {
        Integer pageSize = productFilter.getPageSize() > maxPageSize ? maxPageSize : productFilter.getPageSize();
        Pageable pageable = PageRequest.of(productFilter.getPageNumber(), pageSize);

        Page<ProductPricing> productPricingList = productPricingRepository.findAll(ProductSpec.getProductByCategory(productFilter), pageable);

        return productPricingList.map(p -> {
            ProductDto productDto = new ProductDto();
            productDto.setName(p.getCompanyProduct().getProduct().getName());
            productDto.setId(p.getCompanyProduct().getProduct().getId());
            return productDto;
        });

    }

    @Override
    @Transactional
    public Page<ProductDto> findProductByCompanyAndCategory(Long companyId, Long categoryId, ProductFilter productFilter) {

      //  Integer pageSize = productFilter.getPageSize() > maxPageSize ? maxPageSize : productFilter.getPageSize();
      //  Pageable pageable = PageRequest.of(productFilter.getPageNumber(), pageSize);
        Page<ProductDto> productDtoPage = productRepository.finaProduct(productFilter);
//        Page<ProductRepository.ProductMapper> productList = productRepository.findProductByCompanyAndCategory(companyId, categoryId, pageable);
//        return productList.map(p -> {
//            ProductDto productDto = new ProductDto();
//            productDto.setId(p.getId());
//            productDto.setName(p.getName());
//            productDto.setPrice(p.getPrice());
//            productDto.setDiscountValue(p.getDiscountValue());
//            productDto.setDiscountUnit(p.getDiscountUnit());
//            productDto.setImageLocation(p.getImageLocation());
//            return productDto;
//        });

        return productDtoPage;
    }
}
