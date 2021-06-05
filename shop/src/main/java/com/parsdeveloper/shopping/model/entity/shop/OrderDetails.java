package com.parsdeveloper.shopping.model.entity.shop;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
public class OrderDetails {
    private Long id;
    private Long price;
    private Integer quantity;
    private String sku;
    private CompanyProduct companyProduct;
    private Order order;

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    @ManyToOne
    @JoinColumn(name = "COMPANY_PRODUCT_ID")
    public CompanyProduct getCompanyProduct() {
        return companyProduct;
    }

    public void setCompanyProduct(CompanyProduct companyProduct) {
        this.companyProduct = companyProduct;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
