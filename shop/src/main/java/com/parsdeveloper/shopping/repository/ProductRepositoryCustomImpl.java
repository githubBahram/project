package com.parsdeveloper.shopping.repository;

import com.parsdeveloper.shopping.model.dto.ProductDto;
import com.parsdeveloper.shopping.model.filter.ProductFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.Parameter;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Value("${application.max.page.size}")
    Integer maxPageSize;

    @Value("${image.thumbnail.store.location}")
    String imageThumbnailLocation;

    @Override
    public Page<ProductDto> finaProduct(ProductFilter productFilter) {
        StringBuilder queryBuilder = new StringBuilder();
        StringBuilder countQueryBuilder = new StringBuilder();

        queryBuilder.append("select p.id,p.name,pp.price,pd.discount_value as discountValue,pd.discount_unit as discountUnit ,(select pi.location from product_image  pi where pi.product_id=p.id limit 1 ) as imageLocation from product_pricing pp inner join company_product cp on pp.company_product_id=cp.id inner join product p on cp.id=p.id inner join brand b on p.brand_id=b.id inner join category ca on p.category_id=ca.id left outer join product_discount pd on cp.id=pd.company_product_id where cp.company_id=:companyId and ca.root_id=:categoryId ");
        countQueryBuilder.append("select count(*) from product_pricing pp inner join company_product cp on pp.company_product_id=cp.id inner join product p on cp.id=p.id inner join brand b on p.brand_id=b.id inner join category ca on p.category_id=ca.id left outer join product_discount pd on cp.id=pd.company_product_id where cp.company_id=:companyId and ca.root_id=:categoryId ");

        Integer pageSize = productFilter.getPageSize() > maxPageSize ? maxPageSize : productFilter.getPageSize();

        if (productFilter.getBrandId() != null) {
            queryBuilder.append("and b.id=:brandId").append(" ");
            countQueryBuilder.append("and b.id=:brandId").append(" ");
        }

        Query query = em.createNativeQuery(queryBuilder.toString())
                .setParameter("companyId", productFilter.getCompanyId())
                .setParameter("categoryId", productFilter.getCategoryId());
        Query queryCount = em.createNativeQuery(countQueryBuilder.toString())
                .setParameter("companyId", productFilter.getCompanyId())
                .setParameter("categoryId", productFilter.getCategoryId());

        if (productFilter.getBrandId() != null) {
            query.setParameter("brandId", productFilter.getBrandId());
            queryCount.setParameter("brandId", productFilter.getBrandId());
        }

        query.setFirstResult(productFilter.getPageNumber());
        query.setMaxResults(pageSize);

        List<Object[]> result = query.getResultList();
        List<Object[]> resultCount = queryCount.getResultList();

        List<ProductDto> productDtoList = result.stream().map(p -> {
            ProductDto productDto = new ProductDto();
            productDto.setId(((BigInteger) p[0]).longValue());
            productDto.setName(String.valueOf(p[1]));
            productDto.setPrice(((BigInteger) p[2]).longValue());
            productDto.setDiscountValue((p[3] != null ? ((BigInteger) p[3]).longValue() : null));
            productDto.setDiscountUnit((p[4] != null ? ((BigInteger) p[4]).longValue() : null));
            productDto.setImageLocation(imageThumbnailLocation + p[5]);
            return productDto;
        }).collect(Collectors.toList());

        Pageable pageable=PageRequest.of(productFilter.getPageNumber(),pageSize);
        long total = Long.parseLong(resultCount.get(0)[0].toString());

        return new PageImpl<>(productDtoList,pageable, total);
    }
}
