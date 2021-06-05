package com.parsdeveloper.shopping.model.entity.shop;

import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

public class CompanyProduct extends EffectiveModel<Long> {

    private Product product;
    private Company company;

    @Id
    @Column(unique = true, nullable = false, precision = 19)
    public Long getId() {
        return super.getId();
    }

    @OneToMany
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @OneToMany
    @JoinColumn(name = "company_id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
}
