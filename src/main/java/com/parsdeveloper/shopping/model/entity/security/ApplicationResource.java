package com.parsdeveloper.shopping.model.entity.security;

import com.parsdeveloper.shopping.model.entity.ApplicationSchema;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "APPLICATION_RESOURCE")
@Inheritance(strategy = InheritanceType.JOINED)
public class ApplicationResource extends AuditModel<Long> {

    private String code;
    private String name;
    private ApplicationResource parent;
    private ApplicationSystem applicationSystem;
    private List<ApplicationResource> applicationResourceList;
    private Date effectiveDate;
    private Date disableDate;
    private List<ResourceRole> resourceRoles;



    @Id
    @Column(unique = true, nullable = false, precision = 19)
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

    @Column(name = "NAME", nullable = false)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Column(name = "EFFECTIVE_DATE", nullable = false)
    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    @Column(name = "DISABLE_DATE", nullable = false)
    public Date getDisableDate() {
        return disableDate;
    }

    public void setDisableDate(Date disableDate) {
        this.disableDate = disableDate;
    }

    @ManyToOne
    @JoinColumn(name = "PARENT_ID")
    public ApplicationResource getParent() {
        return parent;
    }

    public void setParent(ApplicationResource parent) {
        this.parent = parent;
    }

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "APPLICATION_SYSTEM_ID", nullable = false, updatable = false)
    public ApplicationSystem getApplicationSystem() {
        return applicationSystem;
    }

    public void setApplicationSystem(ApplicationSystem applicationSystem) {
        this.applicationSystem = applicationSystem;
    }

    @OneToMany(mappedBy = "parent")
    public List<ApplicationResource> getApplicationResourceList() {
        return applicationResourceList;
    }

    public void setApplicationResourceList(List<ApplicationResource> applicationResourceList) {
        this.applicationResourceList = applicationResourceList;
    }

    @OneToMany(mappedBy = "applicationResource")
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

        ApplicationResource applicationResource = (ApplicationResource) o;

        if (getId() != null ? !getId().equals(applicationResource.getId()) : applicationResource.getId() != null)
            return false;
        if (code != null ? !code.equals(applicationResource.code) : applicationResource.code != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + (code != null ? code.hashCode() : 0);
        return result;
    }



}
