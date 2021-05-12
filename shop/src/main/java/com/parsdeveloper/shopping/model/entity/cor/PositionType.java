package com.parsdeveloper.shopping.model.entity.cor;

import com.parsdeveloper.shopping.model.commons.annotations.ViewOption;
import com.parsdeveloper.shopping.model.dao.CodeEnabled;
import com.parsdeveloper.shopping.model.entity.ApplicationSchema;
import com.parsdeveloper.shopping.model.entity.security.ApplicationRole;
import com.parsdeveloper.shopping.model.entity.security.EffectiveModel;
import org.hibernate.validator.constraints.NotBlank;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "POSITION_TYPE")
public class PositionType extends EffectiveModel<Long> implements CodeEnabled {

    private String code;
    private String name;
    private CompanyType companyType;
    private List<ApplicationRole> applicationRoleList;
    private Boolean adminPositionFlag;

    @Id
    @Column(unique = true, nullable = false, precision = 4)
    public Long getId() {
        return super.getId();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @ViewOption(preLoadListOfValue = true)
    @JoinColumn(name = "COMPANY_TYPE_ID", nullable = false)
    @NotNull
    public CompanyType getCompanyType() {
        return companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    @Column(nullable = false, length = 20)
    @Size(max = 20)
    @NotBlank
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(nullable = false, length = 30)
    @Size(max = 20)
    @NotBlank
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "POSITION_ROLE",
            joinColumns = {@JoinColumn(name = "POSITION_TYPE_ID", nullable = false)}
            , inverseJoinColumns = {@JoinColumn(name = "APPLICATION_ROLE_ID", nullable = false)})
    public List<ApplicationRole> getApplicationRoleList() {
        return applicationRoleList;
    }

    public void setApplicationRoleList(List<ApplicationRole> applicationRoleList) {
        this.applicationRoleList = applicationRoleList;
    }

    @Column(name = "ADMIN_POSITION_FLAG")
    public Boolean getAdminPositionFlag() {
        return adminPositionFlag;
    }

    public void setAdminPositionFlag(Boolean adminPositionFlag) {
        this.adminPositionFlag = adminPositionFlag;
    }

}
