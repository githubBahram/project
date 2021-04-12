package com.parsdeveloper.shopping.model.entity.cor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PERSON_UNIT", schema = ApplicationSchema.APPLICATION_SCHEMA)
@Inheritance(strategy = InheritanceType.JOINED)
public class PersonUnit extends EffectiveModel<Long> implements CodeEnabled {

    private String code;
    private String name;
    private Person person;
    private UnitType unitType;
    private String description;
    private CompanyType companyType;
    private PersonUnit parentUnit;
    private PersonUnit root;
    private PersonUnit owner;
    private String ehrCode;
    private String fixName;
    private Boolean testUnitFlag;
    private Zone city;
    private Zone province;

    private List<UserJob> userJobList;
    private List<PersonUnit> subUnits;
    private List<Address> addresses;
    private List<Contact> contacts;
    private List<PersonUnitStatus> personUnitStatuses;
    private UnitStatus unitStatus;


    @Id
    @Column(unique = true, nullable = false, precision = 19)
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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PERSON_UNIT_ADDRESS", schema = ApplicationSchema.APPLICATION_SCHEMA,
            joinColumns = {@JoinColumn(name = "PERSON_UNIT_ID", nullable = false)}
            , inverseJoinColumns = {@JoinColumn(name = "ADDRESS_ID", nullable = false)})
    public List<Address> getAddresses() {
        return this.addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PERSON_UNIT_CONTACT", schema =ApplicationSchema.APPLICATION_SCHEMA,
            joinColumns = {@JoinColumn(name = "PERSON_UNIT_ID", nullable = false)}
            , inverseJoinColumns = {@JoinColumn(name = "CONTACT_ID", nullable = false)})
    public List<Contact> getContacts() {
        return this.contacts;
    }

    public void setContacts(List<Contact> contacts) {
        this.contacts = contacts;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    @JsonIgnore
    public PersonUnit getParentUnit() {
        return this.parentUnit;
    }

    public void setParentUnit(PersonUnit personUnit) {
        this.parentUnit = personUnit;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOT_ID")
    @JsonIgnore
    public PersonUnit getRoot() {
        return root;
    }

    public void setRoot(PersonUnit root) {
        this.root = root;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "OWNER_UNIT_ID")
    @JsonIgnore
    public PersonUnit getOwner() {
        return owner;
    }

    public void setOwner(PersonUnit owner) {
        this.owner = owner;
    }

    @OneToMany(mappedBy = "unit")
    public List<UserJob> getUserJobList() {
        return userJobList;
    }

    public void setUserJobList(List<UserJob> userJobList) {
        this.userJobList = userJobList;
    }

    @OneToMany(mappedBy = "owner")
    public List<PersonUnit> getSubUnits() {
        return this.subUnits;
    }

    public void setSubUnits(List<PersonUnit> personUnits) {
        this.subUnits = personUnits;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID", nullable = false)
    public Person getPerson() {
        return this.person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UNIT_TYPE_ID", nullable = false)
    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }

    @Column(name = "DESCRIPTION", length = 120)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPANY_TYPE_ID", nullable = false)
    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    @Column(name = "EHR_CODE", length = 20)
    public String getEhrCode() {
        return ehrCode;
    }

    public void setEhrCode(String ehrCode) {
        this.ehrCode = ehrCode;
    }

    @Column(name = "FIX_NAME", length = 60)
    public String getFixName() {
        return fixName;
    }

    public void setFixName(String fixName) {
        this.fixName = fixName;
    }

    @Column
    public Boolean getTestUnitFlag() {
        return testUnitFlag;
    }

    public void setTestUnitFlag(Boolean testUnitFlag) {
        this.testUnitFlag = testUnitFlag;
    }


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CITY_ID")
    public Zone getCity() {
        return city;
    }

    public void setCity(Zone city) {
        this.city = city;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROVINCE_ID")
    public Zone getProvince() {
        return province;
    }

    public void setProvince(Zone province) {
        this.province = province;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "personUnit")
    public List<PersonUnitStatus> getPersonUnitStatuses() {
        return personUnitStatuses;
    }

    public void setPersonUnitStatuses(List<PersonUnitStatus> personUnitStatuses) {
        this.personUnitStatuses = personUnitStatuses;
    }

    @JoinColumn(name = "UNIT_STATUS_ID")
    @ManyToOne(fetch = FetchType.LAZY)
    public UnitStatus getUnitStatus() {
        return unitStatus;
    }

    public void setUnitStatus(UnitStatus unitStatus) {
        this.unitStatus = unitStatus;
    }

}