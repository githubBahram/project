package com.parsdeveloper.shopping.model.filter;

public class ProductFilter {
    private Integer pageNumber=1;
    private Integer pageSize=20;
    private Long brandId;
    private Long companyId;
    private Long categoryId;
    private Boolean hasDiscountValue;

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

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
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
}
