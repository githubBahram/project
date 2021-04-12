package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;


@Entity
@Table(name = "BANK", schema = ApplicationSchema.APPLICATION_SCHEMA)
public class Bank extends EffectiveModel<Long> implements CodeEnabled {

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

    @Column(name = "NAME", nullable = false, length = 60)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public enum Code {
        AYANDEH,
        EGHTESADNOVIN,
        IRANZAMIN,
        TEJARAT,
        TOSESADERAT,
        REFAH,
        SEPAH,
        SADERAT,
        KESHAVARZI,
        MASKAN,
        MELLI,
        ANSAR,
        RESALAT,
        SHAHR,
        SANAT,
        MELLAT,
        PASARGAD,
        TOSETAAVON,
        ANSARI,
        DAY,
        SAMAN,
        SARMAYEH,
        SINA,
        MEHRIRAN,
        BONIAD,
        ASGARIE,
        PARSIAN,
        POST,
        KARAFARIN,
        GARDESHGARI,
        MARKAZI,
        UNKNOWN
    }
}