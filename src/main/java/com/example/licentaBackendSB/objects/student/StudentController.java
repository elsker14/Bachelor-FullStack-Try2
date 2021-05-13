package com.example.licentaBackendSB.objects.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    //Field
    private final StudentService studentService;

    //Constructor
    @Autowired
    public StudentController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    //Metoda pentru a afisa toti studentii din baza de date
    @GetMapping
    @PreAuthorize("hasRole('ROLE_STUDENT')")
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    }
}
