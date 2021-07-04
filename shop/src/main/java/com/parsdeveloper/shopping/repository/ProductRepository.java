package com.parsdeveloper.shopping.repository;

import com.parsdeveloper.shopping.model.dto.ProductDto;
import com.parsdeveloper.shopping.model.entity.shop.Product;
import com.parsdeveloper.shopping.model.entity.shop.ProductPricing;
import com.parsdeveloper.shopping.model.filter.ProductSpec;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends ApplicationRepository<Product>, JpaSpecificationExecutor, ProductRepositoryCustom {

    Page<Product> findAll(Pageable pageable);

    Page<Product> findAll(Specification specification, Pageable pageable);

    @Query(value = "select p.id,p.name,pp.price,pd.discount_value as discountValue,pd.discount_unit as discountUnit ,(select pi.location from product_image  pi where pi.product_id=p.id limit 1 ) as imageLocation from product_pricing pp inner join company_product cp on pp.company_product_id=cp.id inner join product p on cp.id=p.id inner join category ca on p.category_id=ca.id left outer join product_discount pd on cp.id=pd.company_product_id where cp.company_id=:companyId and ca.root_id=:categoryId",
            nativeQuery = true,
            countQuery = "select count(*) from product_pricing pp inner join company_product cp on pp.company_product_id=cp.id inner join product p on cp.id=p.id inner join category ca on p.category_id=ca.id left outer join product_discount pd on cp.id=pd.company_product_id where cp.company_id=:companyId and ca.root_id=:categoryId")
    Page<ProductMapper> findProductByCompanyAndCategory(@Param("companyId") Long companyId, @Param("categoryId") Long categoryId, Pageable pageable);

    interface ProductMapper {
        Long getId();

        String getName();

        Long getPrice();

        Long getDiscountValue();

        Long getDiscountUnit();

        String getImageLocation();
    }
}
