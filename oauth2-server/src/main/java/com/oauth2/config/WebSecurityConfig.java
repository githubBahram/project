package com.oauth2.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import javax.servlet.Filter;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String OAUTH_TOKEN = "/oauth/token";

    private final PasswordEncoder passwordEncoder;

//    @Value(("${security.unsecuredUrls}"))
    private List<String> unsecuredUrls=new ArrayList<>();

    public WebSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Lazy
    @Bean("authenticationManager")
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
       return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.authorizeRequests()
//                .antMatchers(OAUTH_TOKEN).permitAll()
//                .anyRequest().authenticated();
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers(getUnsecuredUrls())
                .permitAll();
    }

    private String[] getUnsecuredUrls(){
        if(!unsecuredUrls.contains(OAUTH_TOKEN)){
            unsecuredUrls.add(OAUTH_TOKEN);
        }
        return unsecuredUrls.toArray(new String[0]);
    }


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceBean())
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
        userDetailsManager.createUser(User.withUsername("test")
                .password(passwordEncoder.encode("test"))
                .authorities("test").build());
        return userDetailsManager;
    }

//    @Bean
//    public FilterRegistrationBean<Filter> lastModificationDatyeFilter(){
//        FilterRegistrationBean<Filter> filterFilterRegistrationBean=new FilterRegistrationBean<>();
//        filterFilterRegistrationBean.setFilter(checkUserLastModificationDateFilter);
//        filterFilterRegistrationBean.addUrlPatterns(OAUTH_TOKEN);
//        return filterFilterRegistrationBean;
//    }
}
