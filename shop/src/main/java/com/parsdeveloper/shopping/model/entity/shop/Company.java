package com.parsdeveloper.shopping.model.entity.shop;

import com.parsdeveloper.shopping.model.entity.cor.Address;
import com.parsdeveloper.shopping.model.entity.security.AuditModel;

import javax.persistence.*;

public class Company extends AuditModel<Long> {

    private String name;
    private String code;
    private Address address;

    @Id
    @Column(unique = true, nullable = false, precision = 19)
    public Long getId() {
        return super.getId();
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID", nullable = false)
    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

}
