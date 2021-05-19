package com.example.licentaBackendSB.controllers;

import com.example.licentaBackendSB.entities.Student;
import com.example.licentaBackendSB.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String getStudentView()
    {
        return "pages/admin";
    }

    @GetMapping("students")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ASSISTANT')")
    public String getStudents(Model model)
    {
        //System.out.println("GET: getStudents");
        List<Student> studentsDB = studentService.getStudents();
        model.addAttribute("listOfStudents", studentsDB);
        model.addAttribute("isAdmin", "admin");

        return "pages/students_list";
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
        System.out.println("Am intrat in DELETE: deleteStudent ca ADMIN");
        studentService.deleteStudent(id);
        return "redirect:/admin/students";
    }

    @GetMapping(path = "/students/edit/{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public String editStudent(
            @PathVariable("studentId") Long studentId,
            Model model)
    {
        //System.out.println("PUT: updateStudent");
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
        System.out.println("Am intrat in PUT: updateStudent ca ADMIN");
        studentService.updateStudent(studentId, newStudent);

        return "redirect:/admin/students";
    }
}