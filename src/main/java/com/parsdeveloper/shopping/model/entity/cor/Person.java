package com.parsdeveloper.shopping.model.entity.cor;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "PERSON", schema = ApplicationSchema.APPLICATION_SCHEMA)
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.INTEGER, name = "PERSON_TYPE_ID")
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = LegalPerson.class, name = "legal"),
        @JsonSubTypes.Type(value = NaturalPerson.class, name = "natural")
})
public class Person extends EffectiveModel<Long> {

    private String uniqueField;
    private PersonType personType;
    private String personName;
    private String fixedName;
    private Person root;
    private Person parent;
    private List<Contact> contactList;
    private List<Address> addressList;

    @Id
    @Column(unique = true, nullable = false, precision = 19)
    public Long getId() {
        return super.getId();
    }

    @Column(name = "PERSON_NAME", nullable = false, length = 120)
    public String getPersonName() {
        return this.personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Column(name = "UNIQUE_FIELD", nullable = false, length = 100)
    public String getUniqueField() {
        return this.uniqueField;
    }

    public void setUniqueField(String uniqueField) {
        this.uniqueField = uniqueField;
    }

    // bi-directional many-to-one association to Person
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ROOT_ID")
    @JsonIgnore
    public Person getRoot() {
        return this.root;
    }

    public void setRoot(Person root) {
        this.root = root;
    }

    // bi-directional many-to-one association to Person
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PARENT_ID")
    @JsonIgnore
    public Person getParent() {
        return this.parent;
    }

    public void setParent(Person parent) {
        this.parent = parent;
    }

    // bi-directional many-to-one association to PersonType
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_TYPE_ID", insertable = false, updatable = false)
    public PersonType getPersonType() {
        return this.personType;
    }

    public void setPersonType(PersonType personType) {
        this.personType = personType;
    }


    @Column(name = "PERSON_FIXED_NAME", length = 120, nullable = false)
    public String getFixedName() {
        return this.fixedName;
    }

    public void setFixedName(String fixedName) {
        this.fixedName = fixedName;
    }


    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PERSON_CONTACT",
            schema = ApplicationSchema.APPLICATION_SCHEMA,
            joinColumns = @JoinColumn(name = "PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name = "CONTACT_ID"))
    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "PERSON_ADDRESS",
            schema = ApplicationSchema.APPLICATION_SCHEMA,
            joinColumns = @JoinColumn(name = "PERSON_ID"),
            inverseJoinColumns = @JoinColumn(name = "ADDRESS_ID"))
    public List<Address> getAddressList() {
        return addressList;
    }

    public void setAddressList(List<Address> addressList) {
        this.addressList = addressList;
    }

}