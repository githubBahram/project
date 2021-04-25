package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity

@Table(name = "UNIT_POSITION_TYPE")

public class UnitPositionType extends EffectiveModel<Long> {

    private PositionType positionType;
    private UnitType unitType;

    @Id
    @Column(unique = true, nullable = false, precision = 19)
    public Long getId() {
        return super.getId();
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIT_TYPE_ID", nullable = false)
    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
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
