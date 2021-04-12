package com.parsdeveloper.shopping.model.commons.authorize;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component("commonAuthorizeService")
public class DefaultCommonAuthorizeService implements CommonAuthorizeService {

    private final String USER_MANAGEMENT_AUTHORITY_PATTERN = "^FRM_CRUD(_)?[A-Z]*_USER_MANAGEMENT$";
    private final Pattern USER_MANAGEMENT_PATTERN = Pattern.compile(USER_MANAGEMENT_AUTHORITY_PATTERN);

    @Override
    public final boolean hasAnyUserManagementAuthority(UserDetails principal) {
        return principal.getAuthorities().stream().anyMatch(authority -> USER_MANAGEMENT_PATTERN.matcher(authority.getAuthority()).matches());
    }

}
