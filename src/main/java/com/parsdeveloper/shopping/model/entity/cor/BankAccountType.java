package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "BANK_ACCOUNT_TYPE", schema = ApplicationSchema.APPLICATION_SCHEMA)
public class BankAccountType extends EffectiveModel<Long> implements CodeEnabled {

    private String code;
    private String name;

    public BankAccountType(Long id, String name) {
        setId(id);
        this.name = name;
    }

    public BankAccountType() {
    }

    @Id
    @Column(unique = true, nullable = false, precision = 4)
    public Long getId() {
        return super.getId();
    }

    @Column(name = "CODE", length = 20)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "NAME", length = 60)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Code {
        CURRENT, SHORT_TERM, LONG_TERM
    }
}
