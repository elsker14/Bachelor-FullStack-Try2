package com.example.licentaBackendSB.others;

import org.springframework.security.core.context.SecurityContextHolder;

//Aceasta este dedicata preluarii si prelucarii informatiilor din sesiunea de logare actuala
public class LoggedAccount {
    public String loggedUsername;

    public LoggedAccount() {
        this.loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
    }
}
