package com.parsdeveloper.shopping.model.filter;

import java.util.List;

public class ProductFilter {
    private Integer pageNumber = 1;
    private Integer pageSize = 20;
    private List<Long> brands;
    private Long companyId;
    private Long categoryId;
    private Boolean hasDiscountValue;
    private Boolean rootCategory = false;

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<Long> getBrands() {
        return brands;
    }

    public void setBrands(List<Long> brands) {
        this.brands = brands;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Boolean getHasDiscountValue() {
        return hasDiscountValue;
    }

    public void setHasDiscountValue(Boolean hasDiscountValue) {
        this.hasDiscountValue = hasDiscountValue;
    }

    public Boolean getRootCategory() {
        return rootCategory;
    }

    public void setRootCategory(Boolean rootCategory) {
        this.rootCategory = rootCategory;
    }
}
