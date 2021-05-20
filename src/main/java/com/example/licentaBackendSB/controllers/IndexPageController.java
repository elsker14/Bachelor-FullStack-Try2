package com.example.licentaBackendSB.controllers;

import org.springframework.stereotype.Controller;
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

    //todo: remove it, din pagina principala doar te loghezi
    @GetMapping("menu")
    public String getMenuView() { return "pages/menu"; }

    @GetMapping("sidebarpage")
    public String getSideBarView() { return "sidebar/testpage"; }

}
