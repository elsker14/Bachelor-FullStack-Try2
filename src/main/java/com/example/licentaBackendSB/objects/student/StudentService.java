package com.example.licentaBackendSB.objects.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    //va face selectul din baza de date si va lua tot !
    public List<Student> getStudents()
    {
        return studentRepository.findAll();
    }
}

//        List<Student> studentsDB = new ArrayList<>();
//        studentsDB = Student.hardcodeStudents();
//
//        return studentsDB;