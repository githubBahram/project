package com.parsdeveloper.shopping.model.entity.security;

import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.FetchProfile;
import org.hibernate.annotations.FetchProfiles;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "APPLICATION_SYSTEM", schema = ApplicationSchema.APPLICATION_SCHEMA)
public class ApplicationSystem extends AuditModel<Long> implements CodeEnabled {

    public static final String APPLICATION_SYS_FETCH_PROFILE = "applicationSystemFetchProfile";

    private String code;
    private String name;
    private Date effectiveDate;
    private Date disableDate;
    private String activationUrl;
    private String allowedUrl;
    private String databaseUsername;
    private ApplicationType applicationType;

    @Id
    @Column(nullable = false)
    public Long getId() {
        return super.getId();
    }

    @Column(updatable = false, nullable = false)
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(updatable = false, nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(nullable = false, updatable = false)
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Column
    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    @Column
    public String getActivationUrl() {
        return activationUrl;
    }

    public void setActivationUrl(String activationUrl) {
        this.activationUrl = activationUrl;
    }

    @Column
    public String getAllowedUrl() {
        return allowedUrl;
    }

    public void setAllowedUrl(String allowedUrl) {
        this.allowedUrl = allowedUrl;
    }

    public String getDatabaseUsername() {
        return databaseUsername;
    }

    @Column
    public void setDatabaseUsername(String databaseUsername) {
        this.databaseUsername = databaseUsername;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_TYPE_ID", nullable = false, updatable = false)
    public ApplicationType getApplicationType() {
        return applicationType;
    }

    public void setApplicationType(ApplicationType applicationType) {
        this.applicationType = applicationType;
    }

}
