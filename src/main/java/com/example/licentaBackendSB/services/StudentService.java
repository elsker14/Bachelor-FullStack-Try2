package com.example.licentaBackendSB.services;

import com.example.licentaBackendSB.entities.Student;
import com.example.licentaBackendSB.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
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
    public void updateStudent(Long studentId, Student newStudent) {
        studentRepository.findById(studentId)
                .map(foundStudent -> {
                    //Validari si Verificari
                    if(newStudent.getNume() != null && newStudent.getNume().length() > 0 && !foundStudent.getNume().equals(newStudent.getNume()))
                        foundStudent.setNume(newStudent.getNume());
                    if(newStudent.getPrenume() != null && newStudent.getPrenume().length() > 0 && !foundStudent.getPrenume().equals(newStudent.getPrenume()))
                        foundStudent.setPrenume(newStudent.getPrenume());
                    //todo: de implementat update pt toate fieldurile, dar asta abia cand o sa avem platforma si o sa tecem datele intr-un form

                    return studentRepository.save(foundStudent);
                }).
                orElseThrow(
                        () -> new IllegalStateException("student with id " + studentId + " does not exist")
                );

    }
}