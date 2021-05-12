package com.parsdeveloper.shopping.model.entity.security;

import com.parsdeveloper.shopping.model.dao.BaseModel;
import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = ApplicationSchema.APPLICATION_SCHEMA)
public class ApplicationType extends BaseModel<Long> implements CodeEnabled {

    private String code;
    private String name;

    @Override
    @Id
    public Long getId() {
        return super.getId();
    }

    @Column(nullable = false, updatable = false, insertable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(nullable = false, updatable = false, insertable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
