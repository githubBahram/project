package com.parsdeveloper.shopping.repository;

import com.parsdeveloper.shopping.model.entity.shop.CompanyProduct;
import com.parsdeveloper.shopping.model.entity.shop.Product;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CompanyProductRepository extends ApplicationRepository<CompanyProduct> {

}
