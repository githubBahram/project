package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "CONTACT", schema = ApplicationSchema.APPLICATION_SCHEMA)
public class Contact extends EffectiveModel<Long> {

    private String contactValue;
    private ContactType contactType;


    @Id
    @Column(unique = true, nullable = false, precision = 19)
    public Long getId() {
        return super.getId();
    }

    @Column(name = "CONTACT_VALUE", nullable = false, length = 100)
    public String getContactValue() {
        return this.contactValue;
    }

    public void setContactValue(String value) {
        this.contactValue = value;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CONTACT_TYPE_ID", nullable = false)
    public ContactType getContactType() {
        return this.contactType;
    }

    public void setContactType(ContactType contactType) {
        this.contactType = contactType;
    }

}