package com.example.licentaBackendSB.controllers;

import com.example.licentaBackendSB.entities.*;
import com.example.licentaBackendSB.others.LoggedAccount;
import com.example.licentaBackendSB.others.Validator;
import com.example.licentaBackendSB.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping(path = "/student/mypage")
public class MyPageController {

    //Fields
    private final StudentService studentService;
    private final StudentAccountService studentAccountService;
    private final CaminLeuAService caminLeuAService;
    private final CaminLeuCService caminLeuCService;
    private final CaminP20Service caminP20Service ;
    private final CaminP23Service caminP23Service;

    //Constructor
    @Autowired
    public MyPageController(StudentService studentService,
                            StudentAccountService studentAccountService,
                            CaminLeuAService caminLeuAService,
                            CaminLeuCService caminLeuCService,
                            CaminP20Service caminP20Service,
                            CaminP23Service caminP23Service)
    {
        this.studentService = studentService;
        this.studentAccountService = studentAccountService;
        this.caminLeuAService = caminLeuAService;
        this.caminLeuCService = caminLeuCService;
        this.caminP20Service = caminP20Service;
        this.caminP23Service = caminP23Service;
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

    /* ~~~~~~~~~~~ Get Student knowing the ID for setting the Friend Token ~~~~~~~~~~~ */
    @GetMapping(path = "/ft-edit/{studentId}")
    public String editFriendToken(
            @PathVariable("studentId") Long studentId,
            Model model)
    {
        Student selectedStudent = studentService.editStudent(studentId);
        model.addAttribute("selectedStudentById", selectedStudent);

        if(selectedStudent.getFriendToken().equals("null"))
            return "pages/layer 4/info pages/update_friendToken/update_friendToken";

        return "redirect:/student/mypage";
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
            //Cautam Kid#1 dupa id
            Student firstStudent = studentService.editStudent(studentId);

            //Verificam daca camin e !null
            if(!firstStudent.getCaminPreferat().equals("null"))
            {
                //daca nu e null, dam update in tabelul pt caminul respectiv
                switch (firstStudent.getCaminPreferat())
                {
                    case "Leu A":   caminLeuAService.updateFriendTokenOfStudentInCaminLeuA(CaminLeuA.convertStudentToCaminLeuA(firstStudent));break;
                    case "Leu C":   caminLeuCService.updateFriendTokenOfStudentInCaminLeuC(CaminLeuC.convertStudentToCaminLeuC(firstStudent));break;
                    case "P20":     caminP20Service.updateFriendTokenOfStudentInCaminP20(CaminP20.convertStudentToCaminP20(firstStudent));break;
                    case "P23":     caminP23Service.updateFriendTokenOfStudentInCaminP23(CaminP23.convertStudentToCaminP23(firstStudent));break;
                }
            }
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

        if(!firstStudent.getFriendToken().equals("null") /*&& !secondStudent.get().getFriendToken().equals("null")*/)
        {
            //Setam local "null" la Kid#1
            firstStudent.setFriendToken("null");
            //Updatam in db Kid#1 cu campul friendToken din Kid#1 local
            studentService.clearFriendToken(firstStudent.getId(), firstStudent);

            //Verificam daca camin e !null
            if(!firstStudent.getCaminPreferat().equals("null"))
            {
                //daca nu e null, dam update in tabelul pt caminul respectiv
                switch (firstStudent.getCaminPreferat())
                {
                    case "Leu A":   caminLeuAService.updateFriendTokenOfStudentInCaminLeuA(CaminLeuA.convertStudentToCaminLeuA(firstStudent));break;
                    case "Leu C":   caminLeuCService.updateFriendTokenOfStudentInCaminLeuC(CaminLeuC.convertStudentToCaminLeuC(firstStudent));break;
                    case "P20":     caminP20Service.updateFriendTokenOfStudentInCaminP20(CaminP20.convertStudentToCaminP20(firstStudent));break;
                    case "P23":     caminP23Service.updateFriendTokenOfStudentInCaminP23(CaminP23.convertStudentToCaminP23(firstStudent));break;
                }
            }
        }

        return "redirect:/student/mypage";
    }

    /* ~~~~~~~~~~~ Get Student knowing the ID for setting the Camin ~~~~~~~~~~~ */
    @GetMapping(path = "/camin-edit/{studentId}")
    public String editCamin(
            @PathVariable("studentId") Long studentId,
            Model model)
    {
        Student selectedStudent = studentService.editStudent(studentId);
        model.addAttribute("selectedStudentById", selectedStudent);

        if(selectedStudent.getCaminPreferat().equals("null"))
        {
            return "pages/layer 4/info pages/update_camin/update_camin";
        }


        return "redirect:/student/mypage";
    }

    /* ~~~~~~~~~~~ Update Student Camin and Redirect to MyPage ~~~~~~~~~~~ */
    @PostMapping(path = "/camin-update/{studentId}")
    public String updateCamin(
            @PathVariable("studentId") Long studentId,
            Student newStudent,
            Model model)
    {
        if(Validator.checkCaminSpelling(newStudent.getCaminPreferat()))
        {
            studentService.updateCamin(studentId, newStudent);

            //Preluam studentul care isi adauga camin
            Student selectedStudent = studentService.editStudent(studentId);
            //Modificam local din ce avea la camin cu ce a ales
            selectedStudent.setCaminPreferat(newStudent.getCaminPreferat());

            //Introducem studentul local cu caminul modificat in tabelul corespunzator
            switch (selectedStudent.getCaminPreferat())
            {
                case "Leu A":   caminLeuAService.introduceNewStudentCaminLeuA(CaminLeuA.convertStudentToCaminLeuA(selectedStudent)); break;
                case "Leu C":   caminLeuCService.introduceNewStudentCaminLeuC(CaminLeuC.convertStudentToCaminLeuC(selectedStudent)); break;
                case "P20":     caminP20Service.introduceNewStudentCaminP20(CaminP20.convertStudentToCaminP20(selectedStudent)); break;
                case "P23":     caminP23Service.introduceNewStudentCaminP23(CaminP23.convertStudentToCaminP23(selectedStudent)); break;
            }
        }

        return "redirect:/student/mypage";
    }

    /* ~~~~~~~~~~~ Clear Camin and Update with null and Redirect to MyPage ~~~~~~~~~~~ */
    @RequestMapping(path = "/camin-clear/{studentId}")
    public String clearCamin(
            @PathVariable("studentId") Long studentId)
    {
        Student selectedStudent = studentService.editStudent(studentId);
        if(!selectedStudent.getCaminPreferat().equals("null"))
        {
            switch (selectedStudent.getCaminPreferat())
            {
                case "Leu A":   caminLeuAService.deleteStudentInCaminLeuA(CaminLeuA.convertStudentToCaminLeuA(selectedStudent)); break;
                case "Leu C":   caminLeuCService.deleteStudentInCaminLeuC(CaminLeuC.convertStudentToCaminLeuC(selectedStudent)); break;
                case "P20":     caminP20Service.deleteStudentInCaminP20(CaminP20.convertStudentToCaminP20(selectedStudent)); break;
                case "P23":     caminP23Service.deleteStudentInCaminP23(CaminP23.convertStudentToCaminP23(selectedStudent)); break;
            }

            //Intai stergem din tabel persoana respectiva si dupa ii stergem optiunea aleasa
            selectedStudent.setCaminPreferat("null");
            studentService.clearCamin(selectedStudent.getId(), selectedStudent);
        }

        return "redirect:/student/mypage";
    }
}
