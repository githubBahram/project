package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "COMPANY_TYPE")

public class CompanyType extends EffectiveModel<Long> implements CodeEnabled {

    private String code;
    private String name;

    @Id
    @Column(unique = true, nullable = false, precision = 4)
    public Long getId() {
        return super.getId();
    }


    @Column(name = "CODE", nullable = false, length = 20)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "NAME", nullable = false, length = 30)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Code {
        HCP("HCP"), TPA("TPA"), INSURER("INSURER"), ASSESSOR("ASSESSOR"), POLICYHOLDER("POLICYHOLDER"), BROKER("BROKER");

        private String val;

        Code(String code) {
            this.val = code;
        }

        public String getVal() {
            return this.val;
        }
    }
}
