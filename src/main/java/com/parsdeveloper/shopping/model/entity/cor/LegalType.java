package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;

@Entity
@Table(name = "LEGAL_TYPE", schema = ApplicationSchema.APPLICATION_SCHEMA)
public class LegalType extends EffectiveModel<Long> implements CodeEnabled {

    private String code;
    private String name;
    private String ehrCode;

    @Id
    @Column(unique = true, nullable = false, precision = 4)
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false, length = 20)
    @NaturalId
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(nullable = false, length = 60)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "EHR_CODE", length = 20)
    public String getEhrCode() {
        return ehrCode;
    }

    public void setEhrCode(String ehrCode) {
        this.ehrCode = ehrCode;
    }

    public enum Code {
        ORGANIZATION, CHARITY, GOVERNMENTAL, PRIVATE, PROFIT, NON_PROFIT, UNKNOWN;
    }

}