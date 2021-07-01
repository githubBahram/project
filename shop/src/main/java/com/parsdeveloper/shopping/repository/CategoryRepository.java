package com.parsdeveloper.shopping.repository;

import com.parsdeveloper.shopping.model.entity.shop.Category;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends ApplicationRepository<Category> {

    Page<Category> findAll(Pageable pageable);

    Category findCategoryByFixName(String fixName);

    @Query("select ca from Company c join c.categories ca where ca.id=ca.root.id and c.id=:companyId")
    List<Category> findRootCategoriesByCompany(@Param("companyId") Long companyId);
}
