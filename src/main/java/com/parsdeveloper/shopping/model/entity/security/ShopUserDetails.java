package com.parsdeveloper.shopping.model.entity.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;

public class ShopUserDetails implements UserDetails {

    private Long userId;
    private String password;
    private String username;
    private Long organizationId;
    private Set<GrantedAuthority> authorities;
    private Boolean isEnabled;
    private Boolean isAccountNonExpired;
    private Boolean isAccountNonLocked;
    private Boolean isCredentialsNonExpired;
    private Long userPersonId;
    private Long applicationSystemId;
    private Long organizationPersonId;
    private Boolean isMultiOrganizationUser;


    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }

    public void setAccountNonExpired(Boolean accountNonExpired) {
        isAccountNonExpired = accountNonExpired;
    }

    public void setAccountNonLocked(Boolean accountNonLocked) {
        isAccountNonLocked = accountNonLocked;
    }

    public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
        isCredentialsNonExpired = credentialsNonExpired;
    }

    public void setUserPersonId(Long userPersonId) {
        this.userPersonId = userPersonId;
    }

    public Long getApplicationSystemId() {
        return applicationSystemId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public Boolean getAccountNonExpired() {
        return isAccountNonExpired;
    }

    public Boolean getAccountNonLocked() {
        return isAccountNonLocked;
    }

    public Boolean getCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setApplicationSystemId(Long applicationSystemId) {
        this.applicationSystemId = applicationSystemId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    public Long getUserId() {
        return userId;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.isAccountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.isAccountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.isCredentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isEnabled;
    }

    public Long getUserPersonId() {
        return userPersonId;
    }

    public Long getOrganizationPersonId() {
        return organizationPersonId;
    }

    public void setOrganizationPersonId(Long organizationPersonId) {
        this.organizationPersonId = organizationPersonId;
    }

    public Boolean getMultiOrganizationUser() {
        return isMultiOrganizationUser;
    }

    public void setMultiOrganizationUser(Boolean multiOrganizationUser) {
        isMultiOrganizationUser = multiOrganizationUser;
    }

    public static class DmsUserDetailsBuilder {

        private Long userId;
        private String password;
        private String username;
        private Long organizationId;
        private Set<GrantedAuthority> authorities;
        private Boolean isEnabled;
        private Boolean isAccountNonExpired;
        private Boolean isAccountNonLocked;
        private Boolean isCredentialsNonExpired;
        private Long userPersonId;
        private Long applicationSystemId;
        private Long organizationUserPersonId;
        private Boolean isMultiOrganizationUser;

        public DmsUserDetailsBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public DmsUserDetailsBuilder setUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public DmsUserDetailsBuilder setUsername(String username) {
            this.username = username;
            return this;
        }

        public DmsUserDetailsBuilder setOrganizationId(Long organizationId) {
            this.organizationId = organizationId;
            return this;
        }

        public DmsUserDetailsBuilder setAuthorities(Set<GrantedAuthority> authorities) {
            this.authorities = authorities;
            return this;
        }

        public DmsUserDetailsBuilder setEnabled(Boolean enabled) {
            isEnabled = enabled;
            return this;
        }

        public DmsUserDetailsBuilder setAccountNonExpired(Boolean accountNonExpired) {
            isAccountNonExpired = accountNonExpired;
            return this;
        }

        public DmsUserDetailsBuilder setAccountNonLocked(Boolean accountNonLocked) {
            isAccountNonLocked = accountNonLocked;
            return this;
        }

        public DmsUserDetailsBuilder setCredentialsNonExpired(Boolean credentialsNonExpired) {
            isCredentialsNonExpired = credentialsNonExpired;
            return this;
        }

        public DmsUserDetailsBuilder setUserPersonId(Long userPersonId) {
            this.userPersonId = userPersonId;
            return this;
        }

        public DmsUserDetailsBuilder setApplicationSystemId(Long applicationSystemId) {
            this.applicationSystemId = applicationSystemId;
            return this;
        }

        public DmsUserDetailsBuilder setOrganizationPersonId(Long organizationUserPersonId){
            this.organizationUserPersonId=organizationUserPersonId;
            return this;
        }

        public DmsUserDetailsBuilder setIsMultiOrganizationUser(Boolean isMultiOrganizationUser){
            this.isMultiOrganizationUser=isMultiOrganizationUser;
            return this;
        }

        public ShopUserDetails build() {
            ShopUserDetails userDetails = new ShopUserDetails();
            userDetails.setUserId(this.userId);
            userDetails.setAccountNonExpired(this.isAccountNonExpired);
            userDetails.setAccountNonLocked(this.isAccountNonLocked);
            userDetails.setAuthorities(this.authorities);
            userDetails.setCredentialsNonExpired(this.isCredentialsNonExpired);
            userDetails.setUsername(this.username);
            userDetails.setOrganizationId(this.organizationId);
            userDetails.setEnabled(this.isEnabled);
            userDetails.setPassword(this.password);
            userDetails.setUserPersonId(this.userPersonId);
            userDetails.setApplicationSystemId(this.applicationSystemId);
            userDetails.setMultiOrganizationUser(this.isMultiOrganizationUser);
            userDetails.setOrganizationPersonId(this.organizationUserPersonId);
            return userDetails;
        }
    }

}
