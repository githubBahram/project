package com.parsdeveloper.shopping.model.entity.security;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "USER_ROLE")
public class UserRole extends AuditModel<Long> {

    private User user;
    private ApplicationRole applicationRole;
    private Date effectiveDate;
    private Date disableDate;

    @Id
    @Column(nullable = false, length = 19)
    @Override
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_USER_ID", nullable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_ROLE_ID", nullable = false, updatable = false)
    public ApplicationRole getApplicationRole() {
        return applicationRole;
    }

    public void setApplicationRole(ApplicationRole applicationRole) {
        this.applicationRole = applicationRole;
    }

    @Column(name = "EFFECTIVE_DATE", nullable = false, updatable = false)
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Column(name = "DISABLE_DATE")
    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }
}
