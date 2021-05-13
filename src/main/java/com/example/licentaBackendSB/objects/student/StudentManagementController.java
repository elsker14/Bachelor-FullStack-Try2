package com.example.licentaBackendSB.objects.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "management/api/v1/students")
public class StudentManagementController {

    //Field
    private final StudentService studentService;

    //Constructor
    @Autowired
    public StudentManagementController(StudentService studentService)
    {
        this.studentService = studentService;
    }

    @GetMapping
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
