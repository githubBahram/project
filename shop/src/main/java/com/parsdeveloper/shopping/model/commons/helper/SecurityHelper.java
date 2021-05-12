package com.parsdeveloper.shopping.model.commons.helper;

import com.parsdeveloper.shopping.model.commons.exception.UserNotFoundException;
import com.parsdeveloper.shopping.model.entity.security.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class SecurityHelper {

    public static User getCurrentUserOrThrowException() {
        return getCurrentUser().orElseThrow(UserNotFoundException::new);
    }

    public static Optional<User> getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null /*|| !authentication.isAuthenticated()*/
                || authentication.getPrincipal().equals("anonymousUser")) {
            return Optional.empty();
        }
        return Optional.of((User) authentication.getPrincipal());
    }
}
