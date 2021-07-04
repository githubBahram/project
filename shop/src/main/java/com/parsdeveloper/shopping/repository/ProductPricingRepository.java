package com.parsdeveloper.shopping.repository;

import com.parsdeveloper.shopping.model.entity.shop.ProductPricing;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductPricingRepository extends ApplicationRepository<ProductPricing>, JpaSpecificationExecutor {
}
