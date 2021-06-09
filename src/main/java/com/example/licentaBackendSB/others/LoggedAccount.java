package com.example.licentaBackendSB.others;

import org.springframework.security.core.context.SecurityContextHolder;

//Aceasta este dedicata preluarii si prelucarii informatiilor din sesiunea de logare actuala
public class LoggedAccount {

    //Fields
    private String loggedUsername;

    //Constructor

    public LoggedAccount()
    {
        this.loggedUsername = SecurityContextHolder.getContext().getAuthentication().getName();
    }

    //Getter and Setters
    public String getLoggedUsername() {
        return loggedUsername;
    }

    public void setLoggedUsername(String loggedUsername) {
        this.loggedUsername = loggedUsername;
    }

    //Methods
    public Boolean checkIfStandardAccLogged()
    {
        return this.loggedUsername.equals("checu")
                || this.loggedUsername.equals("iancu")
                || this.loggedUsername.equals("lixi");
    }

    public String getAuthorityOfStandardAcc()
    {
        if(this.loggedUsername.equals("checu"))
            return "STUDENT";
        else if(this.loggedUsername.equals("iancu"))
            return "ADMIN";
        else
            return "ASSISTANT";
    }
}
