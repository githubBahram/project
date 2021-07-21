package com.parsdeveloper.shopping.repository;

import com.parsdeveloper.shopping.model.entity.shop.Brand;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BrandRepository extends ApplicationRepository<Brand> {

    Brand findBrandByFixName(String fixName);

    @Query(value = "select distinct (b) from CompanyProduct cp inner join cp.product p inner join p.brand b where p.category.id=:categoryId and cp.company.id=:companyId ")
    List<Brand> findBrandByCategoryAndCompany(Long categoryId, Long companyId);
}
