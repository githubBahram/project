package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "CURRENCY_TYPE", schema = ApplicationSchema.APPLICATION_SCHEMA)

public class CurrencyType extends EffectiveModel<Long> implements CodeEnabled {

    private String name;
    private String code;
    private Zone country;

    @Id
    @Column(unique = true, nullable = false, precision = 4)
    @Override
    public Long getId() {
        return super.getId();
    }

    @Override
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToOne
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    public Zone getCountry() {
        return country;
    }

    public void setCountry(Zone country) {
        this.country = country;
    }
}
