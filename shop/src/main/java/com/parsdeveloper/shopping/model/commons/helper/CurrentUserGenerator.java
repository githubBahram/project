package com.parsdeveloper.shopping.model.commons.helper;

import com.parsdeveloper.shopping.model.commons.exception.UserNotFoundException;
import com.parsdeveloper.shopping.model.entity.security.ShopUserDetails;
import com.parsdeveloper.shopping.model.entity.security.User;
import org.hibernate.Session;
import org.hibernate.tuple.ValueGenerator;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class CurrentUserGenerator implements ValueGenerator<User> {


    public static final CurrentUserGenerator INSTANCE = new CurrentUserGenerator();

    @Override
    public User generateValue(Session session, Object owner) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null && authentication.getPrincipal() instanceof User) {
            return SecurityHelper.getCurrentUserOrThrowException();
        }else if(authentication!=null && authentication.getPrincipal() instanceof ShopUserDetails){
            return session.find(User.class,((ShopUserDetails) authentication.getPrincipal()).getUserId());
        }else{
            throw new UserNotFoundException();
        }
    }
}
