package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "UNIT_STATUS")
public class UnitStatus extends EffectiveModel<Long> implements CodeEnabled {

    public static final Long ACTIVE_UNIT_STATE = 1L;

    private String code;
    private String name;

    @Id
    @Override
    public Long getId() {
        return super.getId();
    }

    @Column(name = "CODE")
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "NAME")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Code {
        ACTIVE("ACTIVE"),
        INACTIVE("INACTIVE"),
        LEAVE("LEAVE"),
        SUSPENSION("SUSPENSION"),
        AT_SUSPENSION("AT_SUSPENSION"),
        AT_SUSPENSION_CANCEL("AT_SUSPENSION_CANCEL");


        private String value;

        Code(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

    }

}
