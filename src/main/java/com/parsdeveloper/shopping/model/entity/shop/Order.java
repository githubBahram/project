package com.parsdeveloper.shopping.model.entity.shop;

import com.parsdeveloper.shopping.model.entity.cor.Address;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "order")
public class Order extends EffectiveModel<Long> {

    private Long amount;
    private Address address;
    private OrderStatus status;

    @Id
    @Column(unique = true, nullable = false, precision = 19)
    public Long getId() {
        return super.getId();
    }

    @Column
    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STATUS_ID", nullable = false)
    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
