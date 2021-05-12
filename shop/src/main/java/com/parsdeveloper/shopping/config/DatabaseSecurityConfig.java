package com.parsdeveloper.shopping.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.logout.HeaderWriterLogoutHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.security.web.header.writers.ClearSiteDataHeaderWriter;

import javax.sql.DataSource;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

//@EnableWebSecurity()
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class DatabaseSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    protected UserDetailsService userDetailsService() {
        return new CustomUserDetailsService(new JdbcTemplate(dataSource));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().ignoringAntMatchers("/eureka/**")
                .and()
                .authorizeRequests()
                .antMatchers(
                        "/",
                        "/js/**",
                        "/css/**",
                        "/font/**",
                        "/img/**",
                        "/webjars/**").permitAll()
                .antMatchers("/user/**").hasRole("USER")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().accessDeniedPage("/access-denied")
                .and()
                .httpBasic()
                .and()
                .userDetailsService(userDetailsService())
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/")
                .logoutUrl("/logout")
                .logoutSuccessHandler(new SimpleUrlLogoutSuccessHandler())
                .addLogoutHandler(new HeaderWriterLogoutHandler(new ClearSiteDataHeaderWriter(ClearSiteDataHeaderWriter.Directive.ALL)))
                .clearAuthentication(true)
                .and()
                .exceptionHandling()
                .and();
    }


    class CustomUserDetailsService implements UserDetailsService {

        private final JdbcTemplate jdbcTemplate;

        CustomUserDetailsService(JdbcTemplate jdbcTemplate) {
            this.jdbcTemplate = jdbcTemplate;
        }

        @Override
        public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
            try {
                Map<String, Object> map = jdbcTemplate.queryForMap("SELECT au.password FROM CDR.APPLICATION_USER AU WHERE AU.USER_NAME = ?", new Object[]{username});
                return new User(username, (String) map.get("password"), true, true, true, true, createAuthorities(username));
            } catch (EmptyResultDataAccessException ex) {
                throw new UsernameNotFoundException("username not found.", ex);
            }
        }

        private Collection<GrantedAuthority> createAuthorities(String username) {
            Collection<GrantedAuthority> grants = new HashSet<>();
            if (username.equals("developer")) {
                grants.add(new SimpleGrantedAuthority("ROLE_DEVELOPER"));
            } else {
                grants.add(new SimpleGrantedAuthority("ROLE_HELP_WRITER"));
            }
            return grants;
        }

    }

}