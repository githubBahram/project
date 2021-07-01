package com.parsdeveloper.shopping.model.entity.shop;

import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "company_product")
public class CompanyProduct extends EffectiveModel<Long> {

    private Product product;
    private Company company;
    private Integer count;
    private List<ProductDiscount> productDiscountList;


    @Id
    @GeneratedValue
    public Long getId() {
        return super.getId();
    }

    @ManyToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @ManyToOne
    @JoinColumn(name = "company_id")
    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }
    @Column
    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @OneToMany(mappedBy = "companyProduct")
    public List<ProductDiscount> getProductDiscountList() {
        return productDiscountList;
    }

    public void setProductDiscountList(List<ProductDiscount> productDiscountList) {
        this.productDiscountList = productDiscountList;
    }
}
