package com.parsdeveloper.shopping.model.entity.security;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "RESOURCE_ROLE", schema = ApplicationSchema.APPLICATION_SCHEMA)
public class ResourceRole extends AuditModel<Long> {

    private ApplicationRole applicationRole;
    private ApplicationResource applicationResource;
    private Date effectiveDate;
    private Date disableDate;

    @Id
    @Column(unique = true, nullable = false, precision = 10)
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_ROLE_ID")
    public ApplicationRole getApplicationRole() {
        return applicationRole;
    }

    public void setApplicationRole(ApplicationRole applicationRole) {
        this.applicationRole = applicationRole;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_RESOURCE_ID")
    public ApplicationResource getApplicationResource() {
        return applicationResource;
    }

    public void setApplicationResource(ApplicationResource applicationResource) {
        this.applicationResource = applicationResource;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "EFFECTIVE_DATE", nullable = false)
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "DISABLE_DATE")
    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }
}
