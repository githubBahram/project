package com.parsdeveloper.shopping.controller;

import com.parsdeveloper.shopping.model.dto.ProductDto;
import com.parsdeveloper.shopping.model.entity.shop.Product;
import com.parsdeveloper.shopping.model.filter.ProductFilter;
import com.parsdeveloper.shopping.service.api.ImportDataService;
import com.parsdeveloper.shopping.service.api.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/productManager")
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ImportDataService importDataService;

    @GetMapping("/products")
    protected ResponseEntity findAll(Pageable pageable) {
        Page<Product> all = productService.findAll(pageable);
        return ResponseEntity.ok(all);
    }

    @PostMapping("/product")
    public ResponseEntity<Product> save(ProductDto productDto) throws IOException {
        Product product = productService.save(productDto);
        return ResponseEntity.ok(product);
    }

    @GetMapping("/import")
    public ResponseEntity<Product> importData() throws IOException {
        importDataService.importData();
        return ResponseEntity.ok(new Product());
    }

    @GetMapping("/products/companies/{companyId}/categories/{categoryId}")
    public ResponseEntity<Page<ProductDto>> findProductByCompanyAndCategory(@PathVariable Long categoryId, @PathVariable Long companyId, ProductFilter productFilter) {
        Page<ProductDto> productDtoList = productService.findProductByCompanyAndCategory(companyId, categoryId, productFilter);
        return ResponseEntity.ok(productDtoList);
    }

    @GetMapping("/products/companies/{companyId}")
    public ResponseEntity<Page<ProductDto>> findByCompanyId(@PathVariable Long companyId) {
        ProductFilter productFilter = new ProductFilter();
        productFilter.setCompanyId(companyId);
        Page<ProductDto> productDtoPage = productService.findProductByCategory(productFilter);
        return ResponseEntity.ok(productDtoPage);
    }
}
