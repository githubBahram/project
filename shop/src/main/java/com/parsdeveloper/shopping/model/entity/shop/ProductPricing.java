package com.parsdeveloper.shopping.model.entity.shop;

import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "product_pricing")
public class ProductPricing extends EffectiveModel<Long> {

    private Long price;
    private Long basePrice;
    private Boolean inActive;
    private CompanyProduct companyProduct;


    @Id
    @GeneratedValue
    public Long getId() {
        return super.getId();
    }

    @Column
    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Column
    public Long getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(Long basePrice) {
        this.basePrice = basePrice;
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
