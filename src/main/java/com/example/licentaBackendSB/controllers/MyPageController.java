package com.example.licentaBackendSB.controllers;

import com.example.licentaBackendSB.entities.Student;
import com.example.licentaBackendSB.entities.StudentAccount;
import com.example.licentaBackendSB.others.LoggedAccount;
import com.example.licentaBackendSB.services.StudentAccountService;
import com.example.licentaBackendSB.services.StudentService;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.swing.text.html.Option;
import java.util.Optional;

@Controller
@RequestMapping(path = "/student/mypage")
public class MyPageController {

    //Fields
    private final StudentService studentService;
    private final StudentAccountService studentAccountService;

    //Constructor
    @Autowired
    public MyPageController(StudentService studentService, StudentAccountService studentAccountService)
    {
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

            Optional<Student> secondStudent = studentService.findStudentByMyToken(infoStudent.getFriendToken());
            if(secondStudent.isPresent())
                model.addAttribute("yourFriend", secondStudent);
        }

        return "pages/layer 4/info pages/mypage";
    }

    /* ~~~~~~~~~~~ Get Student knowing the ID ~~~~~~~~~~~ */
    @GetMapping(path = "/ft-edit/{studentId}")
    public String editFriendToken(
            @PathVariable("studentId") Long studentId,
            Model model)
    {
        Student selectedStudent = studentService.editStudent(studentId);
        model.addAttribute("selectedStudentById", selectedStudent);

        return "pages/layer 4/info pages/update_friendToken/update_friendToken";
    }

    /* ~~~~~~~~~~~ Update Student Friend Token and Redirect to MyPage ~~~~~~~~~~~ */
    @PostMapping(path = "/ft-update/{studentId}")
    public String updateFriendToken(
            @PathVariable("studentId") Long studentId,
            Student newStudent,
            Model model)
    {
        String isError = studentService.validateFriendToken(newStudent);
        if(isError.equals("All good!"))
        {
            //Kid#1 preia friendTokenul introdus in frontend
            studentService.updateFriendToken(studentId, newStudent);
            //Cautam pe Kid#2 dupa tokenul din frontend
            Optional <Student> secondStudent = studentService.findStudentByMyToken(newStudent.getFriendToken());
            //Cautam Kid#1 dupa id
            Student firstStudent = studentService.editStudent(studentId);
            //Setam LOCAL la Kid#2 friendTokenul de la Kid#1
            secondStudent.get().setFriendToken(firstStudent.getMyToken());
            //Kid#2 preia friendTokenul de la Kid#2 local
            studentService.updateFriendToken(secondStudent.get().getId(), secondStudent.get());
        }

        return "redirect:/student/mypage";
    }

    /* ~~~~~~~~~~~ Clear FriendToken and Update with null and Redirect to MyPage ~~~~~~~~~~~ */
    @RequestMapping(path = "/ft-clear/{studentId}")
    public String clearFriendToken(
            @PathVariable("studentId") Long studentId)
    {
        //Preluam studentul actual adica Kid#1 stiind Id-ul
        Student firstStudent = studentService.editStudent(studentId);
        //Preluam studentul al doilea, adica Kid#2 fiindca cunoastem tokenul lui ce este trecut ca friendToken la Kid#1
        Optional<Student> secondStudent = studentService.findStudentByMyToken(firstStudent.getFriendToken());

        //Setam local "null" la Kid#1
        firstStudent.setFriendToken("null");
        //Setam local "null" la Kid#2
        secondStudent.get().setFriendToken("null");

        //Updatam in db Kid#1 cu campul friendToken din Kid#1 local
        studentService.clearFriendToken(firstStudent.getId(), firstStudent);
        //Updatam in db Kid#2 cu campul friendToken din Kid#1 local
        studentService.clearFriendToken(secondStudent.get().getId(), secondStudent.get());

        return "redirect:/student/mypage";
    }
}
