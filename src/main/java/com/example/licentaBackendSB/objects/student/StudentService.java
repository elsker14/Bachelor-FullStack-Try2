package com.example.licentaBackendSB.objects.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentService {

    //Field
    private final StudentRepository studentRepository;

    //Constructor
    @Autowired
    public StudentService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    //Metoda care va face selectul din baza de date si va lua tot !
    public List<Student> getStudents()
    {
        //select * from student (query in DB)
        List<Student> studentsDB = studentRepository.findAll();

        //sortam lista care vine din DB
        Student.sortStudents(studentsDB);

        return studentsDB;
    }

    public void addNewStudent(Student student) {

        Optional<Student> studentOptional = studentRepository.findStudentByNameAndForname(student.getNume(), student.getPrenume());

        //daca studentul cu exista cu numele respectiv, aruncam exceptie
        if(studentOptional.isPresent())
        {
            throw new IllegalStateException("Student already exists");
        }
        //implicit daca nu exista, il salvam in db
        studentRepository.save(student);
    }

    public void deleteStudent(long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists)
        {
            throw new IllegalStateException("Student with id " + studentId + " doesn't exist");
        }
        studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String forename) {
        Student student = studentRepository.findById(studentId).
                orElseThrow(
                        () -> new IllegalStateException("student with id " + studentId + " does not exist")
                );

        if(name != null && name.length() > 0 && !student.getNume().equals(name))
        {
            student.setNume(name);
        }

        if(forename != null && forename.length() > 0 && !student.getPrenume().equals(forename))
        {
            student.setPrenume(forename);
        }
    }
}