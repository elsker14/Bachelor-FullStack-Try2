package com.example.licentaBackendSB.objects.student;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    public List<Student> getStudents()
    {
        List<Student> studentsDB = new ArrayList<>();
        studentsDB = Student.hardcodeStudents();

        return studentsDB;
    }
}
