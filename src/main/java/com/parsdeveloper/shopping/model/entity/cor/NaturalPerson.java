package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "NATURAL_PERSON", schema = ApplicationSchema.APPLICATION_SCHEMA)
@DiscriminatorValue(value = "1")
@PrimaryKeyJoinColumn(referencedColumnName = "ID")
public class NaturalPerson extends Person {

    private Date birthDate;
    private String certificateNumber;
    private Date deadDate;
    private String fatherName;
    private String fatherNameCorrectNs;
    private String firstName;
    private String firstNameCorrectNs;
    private Boolean deadFlg;
    private String lastName;
    private String lastNameCorrectNs;
    private String nationalCode;
    private GenderType genderType;
    private Zone country;
    private PersonImage personImage;

    public NaturalPerson() {
    }

    public NaturalPerson(Long id) {
        setId(id);
    }

    public static NaturalPerson.Builder getBuilder() {
        return new NaturalPerson.Builder();
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTH_DATE", nullable = false)
    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "CERTIFICATE_NUMBER", nullable = false, length = 20)
    public String getCertificateNumber() {
        return this.certificateNumber;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DEAD_DATE")
    public Date getDeadDate() {
        return this.deadDate;
    }

    public void setDeadDate(Date deaddate) {
        this.deadDate = deaddate;
    }

    @Column(name = "FATHER_NAME", nullable = false, length = 60)
    public String getFatherName() {
        return this.fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    @Column(name = "FATHER_NAME_CORRECT_NS", length = 60)
    public String getFatherNameCorrectNs() {
        return this.fatherNameCorrectNs;
    }

    public void setFatherNameCorrectNs(String fatherNameCorrectNs) {
        this.fatherNameCorrectNs = fatherNameCorrectNs;
    }

    @Column(name = "FIRST_NAME", nullable = false, length = 60)
    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column(name = "FIRST_NAME_CORRECT_NS", length = 60)
    public String getFirstNameCorrectNs() {
        return this.firstNameCorrectNs;
    }

    public void setFirstNameCorrectNs(String firstNameCorrectNs) {
        this.firstNameCorrectNs = firstNameCorrectNs;
    }

    @Column(name = "DEAD_FLG")
    public Boolean getDeadFlg() {
        return this.deadFlg;
    }

    public void setDeadFlg(Boolean isDead) {
        this.deadFlg = isDead;
    }

    @Column(name = "LAST_NAME", nullable = false, length = 120)
    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Column(name = "LAST_NAME_CORRECT_NS", length = 120)
    public String getLastNameCorrectNs() {
        return this.lastNameCorrectNs;
    }

    public void setLastNameCorrectNs(String lastNameCorrectNs) {
        this.lastNameCorrectNs = lastNameCorrectNs;
    }

    @Column(name = "NATIONAL_CODE", nullable = false, length = 13)
    public String getNationalCode() {
        return this.nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GENDER_TYPE_ID", nullable = false)
    public GenderType getGenderType() {
        return this.genderType;
    }

    public void setGenderType(GenderType genderType) {
        this.genderType = genderType;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COUNTRY_ID", nullable = false)
    public Zone getCountry() {
        return country;
    }

    public void setCountry(Zone country) {
        this.country = country;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_IMAGE_ID", nullable = false)
    public PersonImage getPersonImage() {
        return personImage;
    }

    public void setPersonImage(PersonImage personImage) {
        this.personImage = personImage;
    }


    public interface ValidationNationalCodeGroup {
    }

    public interface ValidationEssentialGroup {
    }

    //@GroupSequence({ValidationNationalCodeGroup.class, ValidationEssentialGroup.class})
    public interface ValidationAllGroup {
    }

    public static class Builder {
        NaturalPerson naturalPerson;

        public Builder() {
            naturalPerson = new NaturalPerson();
        }

        public Builder root(NaturalPerson root) {
            naturalPerson.setRoot(root);
            return this;
        }

        public Builder parent(NaturalPerson parent) {
            naturalPerson.setParent(parent);
            return this;
        }

        public Builder birthDate(Date birthDate) {
            naturalPerson.birthDate = birthDate;
            return this;
        }

        public Builder certificateNumber(String certificateNumber) {
            naturalPerson.certificateNumber = certificateNumber;
            return this;
        }

        public Builder deadDate(Date deadDate) {
            naturalPerson.deadDate = deadDate;
            return this;
        }

        public Builder fatherName(String fatherName) {
            naturalPerson.fatherName = fatherName;
            return this;
        }

        public Builder firstName(String firstName) {
            naturalPerson.firstName = firstName;
            return this;
        }

        public Builder deadFlg(Boolean deadFlg) {
            naturalPerson.deadFlg = deadFlg;
            return this;
        }

        public Builder lastName(String lastName) {
            naturalPerson.lastName = lastName;
            return this;
        }

        public Builder personType(PersonType personType) {
            naturalPerson.setPersonType(personType);
            return this;
        }

        public Builder nationalCode(String nationalCode) {
            naturalPerson.nationalCode = nationalCode;
            return this;
        }


        public Builder genderType(GenderType genderType) {
            naturalPerson.genderType = genderType;
            return this;
        }

        public Builder country(Zone country) {
            naturalPerson.country = country;
            return this;
        }

        public Builder personImage(PersonImage personImage) {
            naturalPerson.personImage = personImage;
            return this;
        }

        public NaturalPerson build() {
            return naturalPerson;
        }
    }
}