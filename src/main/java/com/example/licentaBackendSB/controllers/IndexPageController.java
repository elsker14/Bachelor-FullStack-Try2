package com.example.licentaBackendSB.controllers;

import com.example.licentaBackendSB.others.LoggedAccount;
import com.example.licentaBackendSB.security.SecurityConfig;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexPageController {

    @GetMapping("login")
    public String getLoginView()
    {
        return "pages/login";
    }

    @GetMapping("menu")
    public String getMenuView(Model model)
    {
        //Preluam userul din sesiunea actuala
        LoggedAccount loggedAccount = new LoggedAccount();
        model.addAttribute("loggedUsername", loggedAccount.loggedUsername);

        return "pages/menu";
    }

}
