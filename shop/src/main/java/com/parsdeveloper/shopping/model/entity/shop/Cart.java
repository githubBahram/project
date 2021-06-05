package com.parsdeveloper.shopping.model.entity.shop;

import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "cart")
public class Cart extends EffectiveModel<Long> {

    private Integer quantity;
    private String sessionId;
    private CompanyProduct companyProduct;

    @Id
    @Column(unique = true, nullable = false, precision = 19)
    public Long getId() {
        return super.getId();
    }

    @Column
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Column
    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
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
