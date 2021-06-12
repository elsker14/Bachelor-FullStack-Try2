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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.transaction.Transactional;

@Controller
@RequestMapping(path = "/student/mypage")
public class MyPageController {

    //Fields
    private final StudentService studentService;
    private final StudentAccountService studentAccountService;

    //Constructor
    @Autowired
    public MyPageController(StudentService studentService, StudentAccountService studentAccountService) {
        this.studentService = studentService;
        this.studentAccountService = studentAccountService;
    }

    /* ~~~~~~~~~~~ Get MyPage View ~~~~~~~~~~~ */
    @GetMapping
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public String getMyPage(Model model)
    {

        LoggedAccount loggedAccount = new LoggedAccount();

        if(loggedAccount.checkIfStandardAccLogged())
        {
            model.addAttribute("devUsernameAccount", loggedAccount.getLoggedUsername());
        }
        else
        {
            StudentAccount loggedStudentAccount = studentAccountService.getLoggedStudentAccount();
            //query call in db to get info of logged student
            Student infoStudent = studentService.findStudentByNameAndSurname(loggedStudentAccount);

            //getting info about logged acc (credentials) && student info
            model.addAttribute("loggedStudentAccount", loggedStudentAccount);
            model.addAttribute("infoStudent", infoStudent);
            //checkup in case we log in with a dev account
            model.addAttribute("isDevAcc", loggedAccount.checkIfStandardAccLogged().toString());
        }

        return "pages/layer 4/info pages/mypage";
    }

    /* ~~~~~~~~~~~ Get Student knowing the ID ~~~~~~~~~~~ */
    @GetMapping(path = "/edit/{studentId}")
    public String editFriendToken(
            @PathVariable("studentId") Long studentId,
            Model model)
    {
        Student selectedStudent = studentService.editStudent(studentId);
        model.addAttribute("selectedStudentById", selectedStudent);

        return "pages/layer 4/info pages/update_friendToken/update_friendToken";
    }

    /* ~~~~~~~~~~~ Update Student Friend Token and Redirect to MyPage ~~~~~~~~~~~ */
    @PostMapping(path = "/update/{studentId}")
    public String updateFriendToken(
            @PathVariable("studentId") Long studentId,
            Student newStudent,
            Model model)
    {
        studentService.updateFriendToken(studentId, newStudent);

        return "redirect:/student/mypage";
    }

    /* ~~~~~~~~~~~ Clear FriendToken and Update with null and Redirect to MyPage ~~~~~~~~~~~ */
    @RequestMapping(path = "/clear/{studentId}")
    public String clearFriendToken(
            @PathVariable("studentId") Long studentId)
    {
        Student selectedStudent = studentService.editStudent(studentId);
        selectedStudent.setFriendToken("null");
        studentService.clearFriendToken(studentId, selectedStudent);

        return "redirect:/student/mypage";
    }
}
