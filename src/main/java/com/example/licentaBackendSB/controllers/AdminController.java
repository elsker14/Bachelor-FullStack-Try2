package com.example.licentaBackendSB.controllers;

import com.example.licentaBackendSB.entities.Student;
import com.example.licentaBackendSB.services.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
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
    public List<Student> getStudents()
    {
        System.out.println("GET: getStudents");
        return studentService.getStudents();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('student:write')")
    public void registerNewStudent(@RequestBody Student student)
    {
        System.out.println("POST: registerNewStudent");
        studentService.addNewStudent(student);
    }

    @DeleteMapping( path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void deleteStudent(@PathVariable("studentId") Long id)
    {
        System.out.println("DELETE: deleteStudent");
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    @PreAuthorize("hasAuthority('student:write')")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestBody(required = false) Student newStudent)
    {
        System.out.println("PUT: updateStudent");
        studentService.updateStudent(studentId, newStudent);
    }
}
