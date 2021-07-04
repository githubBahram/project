package com.parsdeveloper.shopping.model.filter;

import com.parsdeveloper.shopping.model.entity.shop.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;

public class ProductSpec {

    public static Specification<ProductPricing> getProductByCategory(ProductFilter productFilter) {
        return ((root, criteriaQuery, criteriaBuilder) -> {
            Join<ProductPricing, CompanyProduct> productPricingJoin = root.join(ProductPricing_.companyProduct);
            Join<CompanyProduct, Company> companyJoin = productPricingJoin.join(CompanyProduct_.COMPANY);
            Join<CompanyProduct,Product> productJoin=productPricingJoin.join(CompanyProduct_.PRODUCT, JoinType.INNER);
            Predicate predicate = criteriaBuilder.equal(companyJoin.get(Company_.ID), productFilter.getCompanyId());
            criteriaQuery.distinct(true);
            return predicate;
        }
        );
    }
}
