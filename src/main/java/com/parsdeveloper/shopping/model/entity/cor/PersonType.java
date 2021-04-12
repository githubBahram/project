package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "PERSON_TYPE", schema = ApplicationSchema.APPLICATION_SCHEMA)
public class PersonType extends EffectiveModel<Long> implements CodeEnabled {

    public static final Long NATURAL_TYPE_ID = 1L;
    public static final Long FOREIGN_TYPE_ID = 3L;
    public static final Long LEGAL_TYPE_ID = 2L;

    private String code;
    private String name;

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

    public enum Code{
        NATURAL("NATURAL"),
        FOREIGN("FOREIGN"),
        LEGAL("LEGAL");

        private String value;

        Code(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

}