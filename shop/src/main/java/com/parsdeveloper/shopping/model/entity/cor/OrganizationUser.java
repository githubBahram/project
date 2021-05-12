package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;
import com.parsdeveloper.shopping.model.entity.security.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "ORGANIZATION_USER")
public class OrganizationUser extends EffectiveModel<Long> {

    private Person person;
    private User user;

    private List<UserJob> userJobList;


    @Id
    @Column(unique = true, nullable = false, precision = 19)
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PERSON_ID", nullable = false, updatable = false)
    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID", nullable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "organizationUser")
    public List<UserJob> getUserJobList() {
        return userJobList;
    }

    public void setUserJobList(List<UserJob> userJobList) {
        this.userJobList = userJobList;
    }
}
