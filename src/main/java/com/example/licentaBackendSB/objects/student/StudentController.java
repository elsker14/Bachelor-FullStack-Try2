package com.example.licentaBackendSB.objects.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    }
}
