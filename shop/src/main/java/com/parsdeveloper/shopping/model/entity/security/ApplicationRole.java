package com.parsdeveloper.shopping.model.entity.security;

import com.parsdeveloper.shopping.model.commons.annotations.ViewOption;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "APPLICATION_ROLE")
public class ApplicationRole extends AuditModel<Long> {

    private String code;
    private String name;
    private Boolean disabled;
    private Date effectiveDate;
    private Date disableDate;
    private ApplicationSystem applicationSystem;
    private List<ApplicationResource> applicationResourceList;
    private List<ResourceRole> resourceRoles;


    @Id
    @Column(nullable = false)
    public Long getId() {
        return super.getId();
    }

    @Column(updatable = false, nullable = false)
    @Size(max = 60)
    @NotBlank
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(updatable = false, nullable = false)
    @Size(max = 30)
    @NotBlank
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "EFFECTIVE_DATE", nullable = false, updatable = false)
    @NotNull
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

    @Basic
    @Column(name = "DISABLED_FLG", nullable = false)
    @NotNull
    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @ViewOption(preLoadListOfValue = true)
    @JoinColumn(name = "APPLICATION_SYSTEM_ID", nullable = false, updatable = false)
    @NotNull
    public ApplicationSystem getApplicationSystem() {
        return applicationSystem;
    }

    public void setApplicationSystem(ApplicationSystem applicationSystem) {
        this.applicationSystem = applicationSystem;
    }

    @ManyToMany
    @JoinTable(name = "RESOURCE_ROLE",
            joinColumns = @JoinColumn(name = "APPLICATION_ROLE_ID"),
            inverseJoinColumns = @JoinColumn(name = "APPLICATION_RESOURCE_ID"))
    public List<ApplicationResource> getApplicationResourceList() {
        return applicationResourceList;
    }

    public void setApplicationResourceList(List<ApplicationResource> applicationResourceList) {
        this.applicationResourceList = applicationResourceList;
    }

    @OneToMany(mappedBy = "applicationRole")
    public List<ResourceRole> getResourceRoles() {
        return resourceRoles;
    }

    public void setResourceRoles(List<ResourceRole> resourceRoles) {
        this.resourceRoles = resourceRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ApplicationRole applicationRole = (ApplicationRole) o;

        if (getId() != null ? !getId().equals(applicationRole.getId()) : applicationRole.getId() != null) return false;
        if (code != null ? !code.equals(applicationRole.code) : applicationRole.code != null) return false;
        if (effectiveDate != null ? !effectiveDate.equals(applicationRole.effectiveDate) : applicationRole.effectiveDate != null)
            return false;
        if (disableDate != null ? !disableDate.equals(applicationRole.disableDate) : applicationRole.disableDate != null)
            return false;
        if (disabled != null ? !disabled.equals(applicationRole.disabled) : applicationRole.disabled != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        result = 31 * result + (effectiveDate != null ? effectiveDate.hashCode() : 0);
        result = 31 * result + (disableDate != null ? disableDate.hashCode() : 0);
        result = 31 * result + (disabled != null ? disabled.hashCode() : 0);
        return result;
    }
}
