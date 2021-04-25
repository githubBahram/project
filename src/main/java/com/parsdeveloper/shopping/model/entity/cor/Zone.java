package com.parsdeveloper.shopping.model.entity.cor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ZONE")
public class Zone extends EffectiveModel<Long> implements CodeEnabled {

    private String code;
    private String name;
    private Zone parent;
    private ZoneType type;
    private List<Zone> zones;

    @Id
    @Column(unique = true, nullable = false, precision = 4)
    public Long getId() {
        return super.getId();
    }

    @Column(name = "CODE", nullable = false, length = 20)
    @NaturalId
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

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ZONE_ID")
    public Zone getParent() {
        return this.parent;
    }

    public void setParent(Zone parent) {
        this.parent = parent;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TYPE_ID", nullable = false, updatable = false)
    public ZoneType getType() {
        return type;
    }

    public void setType(ZoneType type) {
        this.type = type;
    }

    public enum Code {
        IRAN("IRAN"), IRAQ("IRAQ"), AFGHANISTAN("AFGHANISTAN");

        private String val;

        Code(String value) {
            this.val = value;
        }

        public String getVal() {
            return this.val;
        }

    }

}