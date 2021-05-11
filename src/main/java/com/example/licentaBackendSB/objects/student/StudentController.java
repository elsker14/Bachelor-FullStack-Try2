package com.example.licentaBackendSB.objects.student;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/students")
public class StudentController {

    @GetMapping
    public List<Student> getStudents()
    {
        List<Student> studentsDB = new ArrayList<>();
        studentsDB = Student.hardcodeStudents();

        return studentsDB;
    }
}
