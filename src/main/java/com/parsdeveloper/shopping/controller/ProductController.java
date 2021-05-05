package com.parsdeveloper.shopping.controller;

import com.parsdeveloper.shopping.model.dto.ProductDto;
import com.parsdeveloper.shopping.model.entity.shop.Product;
import com.parsdeveloper.shopping.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/productManager")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/products")
    protected ResponseEntity findAll(Pageable pageable) {
        Page<Product> all = productService.findAll(pageable);
        return ResponseEntity.ok(all);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> save(ProductDto productDto) throws IOException {
        Product product=productService.save(productDto);
        return ResponseEntity.ok(product);
    }
}
