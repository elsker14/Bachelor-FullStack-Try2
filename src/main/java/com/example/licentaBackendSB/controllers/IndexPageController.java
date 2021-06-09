package com.example.licentaBackendSB.controllers;

import com.example.licentaBackendSB.entities.StudentAccount;
import com.example.licentaBackendSB.others.LoggedAccount;
import com.example.licentaBackendSB.services.StudentAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexPageController {

    //Fields
    private final StudentAccountService studentAccountService;

    //Constructor
    @Autowired
    public IndexPageController(StudentAccountService studentAccountService) {
        this.studentAccountService = studentAccountService;
    }

    @GetMapping("login")
    public String getLoginView()
    {
        return "pages/login";
    }

    @GetMapping("menu")
    public String getMenuView(Model model)
    {
        //Preluam userul din sesiunea actuala si il cautam in baza de date sa scoatem numele si prenumele
        LoggedAccount loggedAccount = new LoggedAccount();
        Boolean isLoggedStandardAcc = loggedAccount.checkIfStandardAccLogged();

        if(isLoggedStandardAcc)
        {
            model.addAttribute("loggedStudentAccount", loggedAccount.getLoggedUsername());
            model.addAttribute("isLoggedStandardAcc", "true");
        }
        else
        {
            StudentAccount loggedStudentAccount = studentAccountService.getLoggedStudentAccount();
            model.addAttribute("loggedStudentAccount", loggedStudentAccount);
            model.addAttribute("isLoggedStandardAcc", "false");
        }
        System.out.println("E LOGAT CONTUL STANDARD? " + isLoggedStandardAcc);
        return "pages/menu";
    }

}
