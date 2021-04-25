package com.parsdeveloper.shopping.model.entity.security;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "CHANGE_PASSWORD_HISTORY")

public class ChangePasswordHistory extends AuditModel<Long> {

    private User user;
    private String action;
    private Date actionDate;
    private Boolean last;

    @Id
    @Column(unique = true, nullable = false, precision = 19)
    @Override
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "ACTION")
    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    @Column(name = "ACTION_DATE")
    public Date getActionDate() {
        return actionDate;
    }

    public void setActionDate(Date actionDate) {
        this.actionDate = actionDate;
    }

    @Column(name = "LAST")
    public Boolean getLast() {
        return last;
    }

    public void setLast(Boolean last) {
        this.last = last;
    }

}