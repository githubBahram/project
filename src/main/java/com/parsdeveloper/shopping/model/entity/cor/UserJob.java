package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "USER_JOB")
public class UserJob extends EffectiveModel<Long> {

    private PersonUnit unit;
    private OrganizationUser organizationUser;
    private UnitPosition unitPosition;
    private String description;

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
    @JoinColumn(name = "ORGANIZATION_USER_ID", nullable = false)
    public OrganizationUser getOrganizationUser() {
        return organizationUser;
    }

    public void setOrganizationUser(OrganizationUser organizationUser) {
        this.organizationUser = organizationUser;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIT_POSITION_ID", nullable = false)
    public UnitPosition getUnitPosition() {
        return unitPosition;
    }

    public void setUnitPosition(UnitPosition unitPosition) {
        this.unitPosition = unitPosition;
    }

    @Column(name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
