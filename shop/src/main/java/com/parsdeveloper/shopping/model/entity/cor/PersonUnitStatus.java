package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;

@Entity
@Table(name = "PERSON_UNIT_STATUS")
public class PersonUnitStatus extends EffectiveModel<Long> {

    private PersonUnit personUnit;
    private UnitStatus unitStatus;
    private String description;
    private byte[] attachedFile;

    @Id
    @Override
    public Long getId() {
        return super.getId();
    }

    @JoinColumn(name = "PERSON_UNIT_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    public PersonUnit getPersonUnit() {
        return personUnit;
    }

    public void setPersonUnit(PersonUnit personUnit) {
        this.personUnit = personUnit;
    }

    @JoinColumn(name = "UNIT_STATUS_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    public UnitStatus getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(UnitStatus unitStatus) {
        this.unitStatus = unitStatus;
    }

    @Column(name = "DESCRPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "ATTACHED_FILE")
    public byte[] getAttachedFile() {
        return attachedFile;
    }

    public void setAttachedFile(byte[] attachedFile) {
        this.attachedFile = attachedFile;
    }
}
