package com.example.licentaBackendSB.services;

import com.example.licentaBackendSB.entities.Student;
import com.example.licentaBackendSB.entities.StudentAccount;
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

    /*  ~~~~~~~~~~~ Get List of Students ~~~~~~~~~~~ */
    public List<Student> getStudents()
    {
        //select * from student (query in DB)
        List<Student> studentsDB = studentRepository.findAll();

        //sortam lista care vine din DB
        Student.sortStudents(studentsDB);

        return studentsDB;
    }

    /*  ~~~~~~~~~~~ Find Student by Name and Surname ~~~~~~~~~~~ */
    public Student findStudentByNameAndSurname(StudentAccount studentAccount)
    {
        Optional<Student> foundStudent = studentRepository.findStudentByNameAndSurname(studentAccount.getNume(), studentAccount.getPrenume());

        if(foundStudent.isPresent())
        {
            return foundStudent.get();
        }
        else
        {
            throw new IllegalStateException("Student doens't exist!");
        }
    }

    /*  ~~~~~~~~~~~ Add new Student ~~~~~~~~~~~ */
    public void addNewStudent(Student student) {

        Optional<Student> studentOptional = studentRepository.findStudentByNameAndSurname(student.getNume(), student.getPrenume());

        //daca studentul cu exista cu numele respectiv, aruncam exceptie
        if(studentOptional.isPresent())
        {
            throw new IllegalStateException("Student already exists");
        }
        //implicit daca nu exista, il salvam in db
        studentRepository.save(student);
    }

    /*  ~~~~~~~~~~~ Delete Student from Student Table ~~~~~~~~~~~ */
    public void deleteStudent(long studentId) {
        boolean exists = studentRepository.existsById(studentId);
        if(!exists)
        {
            throw new IllegalStateException("Student with id " + studentId + " doesn't exist");
        }

        studentRepository.deleteById(studentId);
    }

    /*  ~~~~~~~~~~~ Get Id of Student to update Student && FriendToken ~~~~~~~~~~~ */
    public Student editStudent(Long studentId) {

        return studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid student Id:" + studentId));
    }

    /*  ~~~~~~~~~~~ Update Student ~~~~~~~~~~~ */
    @Transactional
    public void updateStudent(Long studentId, Student newStudent) {
        studentRepository.findById(studentId)
                .map(foundStudent -> {
                    //Validari si Verificari

                    /** update nume*/
                    if(newStudent.getNume() != null
                            && newStudent.getNume().length() > 0
                            && !foundStudent.getNume().equals(newStudent.getNume()))
                    {
                        foundStudent.setNume(newStudent.getNume());
                    }

                    /** update prenume*/
                    if(newStudent.getPrenume() != null
                            && newStudent.getPrenume().length() > 0
                            && !foundStudent.getPrenume().equals(newStudent.getPrenume()))
                    {
                        foundStudent.setPrenume(newStudent.getPrenume());
                    }

                    /** update cnp*/
                    if(newStudent.getCnp() != null
                            && newStudent.getCnp().length() > 0
                            && !foundStudent.getCnp().equals(newStudent.getCnp()))
                    {
                        foundStudent.setCnp(newStudent.getCnp());
                    }

                    /** update zi_de_nastere*/
                    if(newStudent.getZi_de_nastere() != null
                            && newStudent.getZi_de_nastere().length() > 0
                            && !foundStudent.getZi_de_nastere().equals(newStudent.getZi_de_nastere()))
                    {
                        foundStudent.setZi_de_nastere(newStudent.getZi_de_nastere());
                    }

                    /** update an*/
                    if(newStudent.getAn() != null
                            && !foundStudent.getAn().equals(newStudent.getAn()))
                    {
                        foundStudent.setAn(newStudent.getAn());
                    }

                    /** update grupa*/
                    if(newStudent.getGrupa() != null
                            && newStudent.getGrupa().length() > 0
                            && !foundStudent.getGrupa().equals(newStudent.getGrupa()))
                    {
                        foundStudent.setGrupa(newStudent.getGrupa());
                    }

                    /** update serie*/
                    if(newStudent.getSerie() != null
                            && newStudent.getSerie().length() > 0
                            && !foundStudent.getSerie().equals(newStudent.getSerie()))
                    {
                        foundStudent.setSerie(newStudent.getSerie());
                    }

                    /** update judet*/
                    if(newStudent.getJudet() != null
                            && newStudent.getJudet().length() > 0
                            && !foundStudent.getJudet().equals(newStudent.getJudet()))
                    {
                        foundStudent.setJudet(newStudent.getJudet());
                    }

                    return studentRepository.save(foundStudent);
                }).
                orElseThrow(
                        () -> new IllegalStateException("student with id " + studentId + " does not exist")
                );
    }

    /*  ~~~~~~~~~~~ Update Only FriendToken ~~~~~~~~~~~ */
    @Transactional
    public void updateFriendToken(Long studentId, Student newStudent) {
        studentRepository.findById(studentId)
                .map(foundStudent -> {
                    //Validari si Verificari

                    /** update friendToken*/
                    if(newStudent.getFriendToken() != null
                            && newStudent.getFriendToken().length() > 0
                            && !foundStudent.getFriendToken().equals(newStudent.getFriendToken()))
                    {
                        foundStudent.setFriendToken(newStudent.getFriendToken());
                    }

                    return studentRepository.save(foundStudent);
                }).
                orElseThrow(
                        () -> new IllegalStateException("student with id " + studentId + " does not exist")
                );
    }

    /*  ~~~~~~~~~~~ Clear FriendToken ~~~~~~~~~~~ */
    @Transactional
    public void clearFriendToken(Long studentId, Student selectedStudent) {
        studentRepository.findById(studentId)
                .map(foundStudent -> {
                    //Validari si Verificari

                    /** update friendToken*/
                    if(!foundStudent.getFriendToken().equals("null"))
                    {
                        foundStudent.setFriendToken(selectedStudent.getFriendToken());
                    }

                    return studentRepository.save(foundStudent);
                }).
                orElseThrow(
                        () -> new IllegalStateException("student with id " + studentId + " does not exist")
                );
    }
}