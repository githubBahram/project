package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.commons.annotations.ViewOption;
import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "UNIT_TYPE", schema = ApplicationSchema.APPLICATION_SCHEMA)
public class UnitType extends EffectiveModel<Long> implements CodeEnabled {

    private String code;
    private String name;
    private Integer type;
    private CompanyType companyType;
    private String ehrCode;

    @Id
    @Column(unique = true, nullable = false, precision = 4)
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false, length = 20)
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

    @ViewOption(preLoadListOfValue = true)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_TYPE_ID", nullable = false)
    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    @Column(name = "Type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Column(name = "EHR_CODE", length = 20)
    public String getEhrCode() {
        return ehrCode;
    }

    public void setEhrCode(String ehrCode) {
        this.ehrCode = ehrCode;
    }

    public enum Type {
        THERAPEUTIC(1), STAFF(0);
        private Integer value;

        Type(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }
}
