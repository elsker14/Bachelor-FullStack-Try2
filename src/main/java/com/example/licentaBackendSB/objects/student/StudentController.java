package com.example.licentaBackendSB.objects.student;

import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Student> getStudents()
    {
        return studentService.getStudents();
    }

    //luam studentul din request body si il trimitem spre db
    @PostMapping
    public void registerNewStudent(@RequestBody Student student)
    {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long id)
    {
        studentService.deleteStudent(id);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String forename)
    {
        studentService.updateStudent(studentId, name, forename);
    }
}
