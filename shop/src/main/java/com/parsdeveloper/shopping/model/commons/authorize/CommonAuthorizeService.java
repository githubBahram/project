package com.parsdeveloper.shopping.model.commons.authorize;

import org.springframework.security.core.userdetails.UserDetails;

public interface CommonAuthorizeService {

    boolean hasAnyUserManagementAuthority(UserDetails principal);

}
