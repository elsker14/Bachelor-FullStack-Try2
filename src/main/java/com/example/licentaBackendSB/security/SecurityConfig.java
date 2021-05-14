package com.example.licentaBackendSB.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.util.concurrent.TimeUnit;

import static com.example.licentaBackendSB.security.UserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;


    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //configuram CSRF
                .csrf().disable()
                .authorizeRequests()    //vrem sa autorizam requesturi

                //Asta e pagina default la care are ORICINE nelogat acces: http://localhost:8080/
                .antMatchers("/", "index", "/css/*", "/js/*").permitAll()

                .anyRequest()           //orice request venit
                .authenticated()        //trebuie OBLIGATORIU sa fie autentificat

                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/home", true)
                    .passwordParameter("password")      //asta tre sa dea match cu "name" din login.html
                    .usernameParameter("username")

                .and()
                .rememberMe()
                    .tokenValiditySeconds((int)TimeUnit.DAYS.toSeconds(21))    //defaults to 2 weeks
                    .key("somethingVerySecured")   //cheia de criptare pt sessionId si expiration date, deobicei e md5
                    .rememberMeParameter("remember-me")

                .and()
                .logout()
                    .logoutUrl("/logout")
                    .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"))      //doar fiindca CSRF e disabled, daca va fi enabled, musai tre sa fie POST
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login");
    }

    @Override
    @Bean       //will be instantiated for us
    protected UserDetailsService userDetailsService() {
        UserDetails userUser = User.builder()
                .username("checu")
                .password(passwordEncoder.encode("1233"))
                .authorities(STUDENT.getGrantedAuthorities())
                .build();

        UserDetails adminUser = User.builder()
                .username("iancu")
                .password(passwordEncoder.encode("1233"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails assistantUser = User.builder()
                .username("lixi")
                .password(passwordEncoder.encode("1233"))
                .authorities(ASSISTANT.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                userUser,
                adminUser,
                assistantUser
        );
    }
}
