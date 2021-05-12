package com.oauth2.application.outbound.transformer;

import com.oauth2.application.outbound.dto.UserInquiryResponse;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserDtoToUserDetailAssembler {

    public static UserDetails toUserDetails(UserInquiryResponse response) {

        return new UserDetails() {
            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return response.getAuthorities().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toSet());
            }

            @Override
            public String getPassword() {
                return response.getPassword();
            }

            @Override
            public String getUsername() {
                return response.getUsername();
            }

            @Override
            public boolean isAccountNonExpired() {
                return !response.getExpired();
            }

            @Override
            public boolean isAccountNonLocked() {
                return !response.getLocked();
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return response.getEnabled();
            }

            @Override
            public boolean isEnabled() {
                return false;
            }
        };

    }
}
