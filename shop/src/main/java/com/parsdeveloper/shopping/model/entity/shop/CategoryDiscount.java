package com.parsdeveloper.shopping.model.entity.shop;

import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "category_discount")
public class CategoryDiscount extends EffectiveModel<Long> {

    private Long discountValue;
    private Long discountUnit;
    private String CouponCode;
    private Long MinimumOrderValue;
    private Long MaximumDiscountAmount;
    private Boolean inActive;
    private Category category;

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
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
