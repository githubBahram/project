package com.parsdeveloper.shopping.model.entity.shop;

import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "product_discount")
public class ProductDiscount extends EffectiveModel<Long> {

    private Long discountValue;
    private Long discountUnit;
    private String CouponCode;
    private Long MinimumOrderValue;
    private Long MaximumDiscountAmount;
    private Boolean inActive;
    private CompanyProduct companyProduct;

    @Id
    @GeneratedValue
    public Long getId() {
        return super.getId();
    }

    @Column
    public Long getDiscountValue() {
        return discountValue;
    }

    public void setDiscountValue(Long discountValue) {
        this.discountValue = discountValue;
    }

    @Column
    public Long getDiscountUnit() {
        return discountUnit;
    }

    public void setDiscountUnit(Long discountUnit) {
        this.discountUnit = discountUnit;
    }

    @Column
    public String getCouponCode() {
        return CouponCode;
    }

    public void setCouponCode(String couponCode) {
        CouponCode = couponCode;
    }

    @Column
    public Long getMinimumOrderValue() {
        return MinimumOrderValue;
    }

    public void setMinimumOrderValue(Long minimumOrderValue) {
        MinimumOrderValue = minimumOrderValue;
    }

    @Column
    public Long getMaximumDiscountAmount() {
        return MaximumDiscountAmount;
    }

    public void setMaximumDiscountAmount(Long maximumDiscountAmount) {
        MaximumDiscountAmount = maximumDiscountAmount;
    }

    @Column
    public Boolean getInActive() {
        return inActive;
    }

    public void setInActive(Boolean inActive) {
        this.inActive = inActive;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_PRODUCT_ID")
    public CompanyProduct getCompanyProduct() {
        return companyProduct;
    }

    public void setCompanyProduct(CompanyProduct companyProduct) {
        this.companyProduct = companyProduct;
    }

}
