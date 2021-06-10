package com.example.licentaBackendSB.controllers;

import com.example.licentaBackendSB.entities.Student;
import com.example.licentaBackendSB.others.LoggedAccount;
import com.example.licentaBackendSB.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//todo: cand faci o operatie de delete, edit, ar trebui modificarile astea sa fie facute si in tabelul cu conturi studenti!!!!

@Controller
@RequestMapping(path = "admin")
public class AdminController {

    //Field
    private final StudentService studentService;

    //Constructor
    @Autowired
    public AdminController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @GetMapping
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ASSISTANT')")
    public String getStudentView(Model model)
    {
        LoggedAccount loggedAccount = new LoggedAccount();
        model.addAttribute("loggedUsername", loggedAccount.getLoggedUsername());
        model.addAttribute("isDevAcc", loggedAccount.checkIfStandardAccLogged().toString());

        return "pages/admin";
    }

    @GetMapping("students")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ASSISTANT')")
    public String getStudents(Model model)
    {
        List<Student> studentsDB = studentService.getStudents();
        model.addAttribute("listOfStudents", studentsDB);
        model.addAttribute("isAdmin", "admin");

        return "pages/students_list";
    }

    @GetMapping("/devAdminPage")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ASSISTANT')")
    public String getDevAdminPage(Model model)
    {
        return "pages/devAdminPage";
    }

//    @PostMapping
//    @PreAuthorize("hasAuthority('student:write')")
//    public void registerNewStudent(@RequestBody Student student)
//    {
//        System.out.println("POST: registerNewStudent");
//        studentService.addNewStudent(student);
//    }

    @GetMapping( path = "/students/delete/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public String deleteStudent(@PathVariable("studentId") Long id, Model model)
    {
        studentService.deleteStudent(id);
        return "redirect:/admin/students";
    }

    @GetMapping(path = "/students/edit/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public String editStudent(
            @PathVariable("studentId") Long studentId,
            Model model)
    {
        Student selectedStudent = studentService.editStudent(studentId);        //getting student by id
        model.addAttribute("selectedStudentById", selectedStudent);

        return "pages/update_student";
    }

    @PostMapping(path = "/students/update/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public String updateStudent(
            @PathVariable("studentId") Long studentId,
            Student newStudent,
            Model model)
    {
        studentService.updateStudent(studentId, newStudent);

        return "redirect:/admin/students";
    }
}
