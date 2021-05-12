package com.example.licentaBackendSB.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.licentaBackendSB.security.ApplicationUserRole.*;

@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()    //vrem sa autorizam requesturi
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/api/**").hasRole(STUDENT.name())
                .anyRequest()           //orice request venit
                .authenticated()        //trebuie OBLIGATORIU sa fie autentificat
                .and()                  //si
                .httpBasic();           //mecanismul prin care verificam clientul care se logheaza cu userul sau cv pe aici
    }

    @Override
    @Bean       //will be instantiated for us
    protected UserDetailsService userDetailsService() {
         UserDetails userUser = User.builder()
                .username("checu")
                .password(passwordEncoder.encode("1233"))
                .roles(STUDENT.name())           //ROLE_STUDENT
                .build();

        UserDetails adminUser = User.builder()
                .username("iancu")
                .password(passwordEncoder.encode("1233"))
                .roles(ADMIN.name())           //ROLE_ADMIN
                .build();

        return new InMemoryUserDetailsManager(
                userUser,
                adminUser
        );
    }
}
