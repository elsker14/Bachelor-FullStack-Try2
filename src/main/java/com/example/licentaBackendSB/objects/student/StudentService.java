package com.example.licentaBackendSB.objects.student;

import java.util.ArrayList;
import java.util.List;

public class StudentService {

    public List<Student> getStudents()
    {
        List<Student> studentsDB = new ArrayList<>();
        studentsDB = Student.hardcodeStudents();

        return studentsDB;
    }
}
