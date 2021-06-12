package com.example.licentaBackendSB.controllers;

import com.example.licentaBackendSB.entities.Student;
import com.example.licentaBackendSB.entities.StudentAccount;
import com.example.licentaBackendSB.others.LoggedAccount;
import com.example.licentaBackendSB.services.StudentAccountService;
import com.example.licentaBackendSB.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/student")
public class StudentController {

    //Field
    private final StudentService studentService;
    private final StudentAccountService studentAccountService;

    //Constructor
    @Autowired
    public StudentController(StudentService studentService, StudentAccountService studentAccountService)
    {
        this.studentService = studentService;
        this.studentAccountService = studentAccountService;
    }

    /* ~~~~~~~~~~~ StudentView ~~~~~~~~~~~ */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public String getStudentView(Model model)
    {
        LoggedAccount loggedAccount = new LoggedAccount();
        model.addAttribute("loggedUsername", loggedAccount.getLoggedUsername());
        model.addAttribute("isDevAcc", loggedAccount.checkIfStandardAccLogged().toString());

        return "pages/layer 3/student";
    }

    /* ~~~~~~~~~~~ Get list of Students ~~~~~~~~~~~ */
    //Metoda pentru a afisa toti studentii din baza de date
    @GetMapping("/students")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public String getStudents(Model model)
    {
        List<Student> studentsDB = studentService.getStudents();
        model.addAttribute("listOfStudents", studentsDB);
        model.addAttribute("isAdmin", "student");

        return "pages/layer 4/students_list";
    }

    /* ~~~~~~~~~~~ Get devStudentPage View ~~~~~~~~~~~ */
    @GetMapping("/devStudentPage")
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public String getDevStudentPage(Model model)
    {
        return "pages/layer 4/info pages/devStudentPage";
    }
}
