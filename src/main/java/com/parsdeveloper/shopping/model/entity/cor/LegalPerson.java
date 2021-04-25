package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import org.joda.time.LocalDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Date;

@Entity
@Table(name = "LEGAL_PERSON")

@DiscriminatorValue(value = "2")
@PrimaryKeyJoinColumn(referencedColumnName = "ID")
public class LegalPerson extends Person {

    private String aliasName;
    private String economicCode;
    private Date establishDate;
    private String name;
    private String registrationNumber;
    private LegalType legalType;
    private String organizationNationalCode;
    private LegalPersonImage legalPersonImage;

    public static LegalPerson.Builder getBuilder() {
        return new LegalPerson.Builder();
    }

    @Column(name = "ALIAS_NAME", length = 550)
    public String getAliasName() {
        return this.aliasName;
    }

    public void setAliasName(String alias) {
        this.aliasName = alias;
    }

    @Column(name = "ECONOMIC_CODE", nullable = false, length = 20)
    @NotNull(groups = ValidationAllGroup.class)
    public String getEconomicCode() {
        return this.economicCode;
    }

    public void setEconomicCode(String economicCode) {
        this.economicCode = economicCode;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ESTABLISH_DATE")
    public Date getEstablishDate() {
        return this.establishDate;
    }

    public void setEstablishDate(Date establishDate) {
        this.establishDate = establishDate;
    }

    @Column(name = "NAME", nullable = false, length = 120)
    @NotNull(groups = ValidationAllGroup.class)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "REGISTRATION_NUMBER", length = 100)
    @NotNull(groups = ValidationAllGroup.class)
    public String getRegistrationNumber() {
        return this.registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    @Column(name = "ORGANIZATION_NATIONAL_CODE", nullable = false, length = 15)
    @NotNull(groups = ValidationOrganizationNationalCodeGroup.class)
    public String getOrganizationNationalCode() {
        return organizationNationalCode;
    }

    public void setOrganizationNationalCode(String organizationNationalCode) {
        this.organizationNationalCode = organizationNationalCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEGAL_PERSON_IMAGE_ID", nullable = false)
    public LegalPersonImage getLegalPersonImage() {
        return legalPersonImage;
    }

    public void setLegalPersonImage(LegalPersonImage legalPersonImage) {
        this.legalPersonImage = legalPersonImage;
    }

    //bi-directional many-to-one association to LegalType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEGAL_TYPE_ID", nullable = false)
    @NotNull(groups = ValidationAllGroup.class)
    public LegalType getLegalType() {
        return this.legalType;
    }

    public void setLegalType(LegalType legalType) {
        this.legalType = legalType;
    }

    public interface ValidationOrganizationNationalCodeGroup extends Default {
    }

    public interface ValidationAllGroup extends ValidationOrganizationNationalCodeGroup {
    }

    public static class Builder {
        LegalPerson legalPerson;

        public Builder() {
            legalPerson = new LegalPerson();
        }

        public Builder root(LegalPerson root) {
            legalPerson.setRoot(root);
            return this;
        }

        public Builder parent(LegalPerson parent) {
            legalPerson.setParent(parent);
            return this;
        }

        public Builder personType(PersonType personType) {
            legalPerson.setPersonType(personType);
            return this;
        }

        public Builder aliasName(String aliasName) {
            legalPerson.aliasName = aliasName;
            return this;
        }

        public Builder economicCode(String economicCode) {
            legalPerson.economicCode = economicCode;
            return this;
        }

        public Builder establishDate(Date establishDate) {
            legalPerson.establishDate = establishDate;
            return this;
        }

        public Builder name(String name) {
            legalPerson.name = name;
            return this;
        }

        public Builder registrationNumber(String registrationNumber) {
            legalPerson.registrationNumber = registrationNumber;
            return this;
        }

        public Builder legalType(LegalType legalType) {
            legalPerson.legalType = legalType;
            return this;
        }

        public Builder organizationNationalCode(String organizationNationalCode) {
            legalPerson.organizationNationalCode = organizationNationalCode;
            return this;
        }

        public LegalPerson build() {
            if (legalPerson.getEffectiveDate() == null) {
                legalPerson.setEffectiveDate(LocalDate.now().toDate());
            }
            return legalPerson;
        }
    }
}