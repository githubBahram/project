package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "UNIT_POSITION", schema = ApplicationSchema.APPLICATION_SCHEMA)
public class UnitPosition extends EffectiveModel<Long> {

    private PersonUnit unit;
    private PositionType positionType;

    @Id
    @Column(unique = true, nullable = false, precision = 19)
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_UNIT_ID", nullable = false)
    public PersonUnit getUnit() {
        return unit;
    }

    public void setUnit(PersonUnit unit) {
        this.unit = unit;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POSITION_TYPE_ID", nullable = false)
    public PositionType getPositionType() {
        return positionType;
    }

    public void setPositionType(PositionType positionType) {
        this.positionType = positionType;
    }


}
