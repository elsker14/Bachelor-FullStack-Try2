package com.example.licentaBackendSB.services;

import com.example.licentaBackendSB.entities.Student;
import com.example.licentaBackendSB.entities.StudentAccount;
import com.example.licentaBackendSB.others.LoggedAccount;
import com.example.licentaBackendSB.others.randomizers.DoBandCNPandGenderRandomizer;
import com.example.licentaBackendSB.repositories.StudentAccountsDBRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentAccountService {

    //Field
    private final StudentAccountsDBRepository studentAccountsDBRepository;

    //Constructor
    @Autowired
    public StudentAccountService(StudentAccountsDBRepository studentAccountsDBRepository)
    {
        this.studentAccountsDBRepository = studentAccountsDBRepository;
    }

    /*  ~~~~~~~~~~~ Get Logged Account from Current Session ~~~~~~~~~~~ */
    public StudentAccount getLoggedStudentAccount()
    {
        StudentAccount result = new StudentAccount();
        LoggedAccount loggedAccount = new LoggedAccount();

        List<StudentAccount> studentAccountsDB = studentAccountsDBRepository.findAll();
        for(StudentAccount it: studentAccountsDB)
        {
            if(it.getCnp().equals(loggedAccount.getLoggedUsername()))
            {
                result = it;
                break;
            }
        }
        
        return result;
    }

    /*  ~~~~~~~~~~~ Delete Student ~~~~~~~~~~~ */
    public void deleteStudent(long studentId)
    {
        boolean exists = studentAccountsDBRepository.existsById(studentId);
        if(!exists)
        {
            throw new IllegalStateException("StudentAccount with id " + studentId + " doesn't exist");
        }

        studentAccountsDBRepository.deleteById(studentId);
    }

    /*  ~~~~~~~~~~~ Update Student ~~~~~~~~~~~ */
    public void updateStudent(Long studentId, Student newStudent)
    {
        studentAccountsDBRepository.findById(studentId)
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
                        foundStudent.setUsername(newStudent.getCnp());
                    }

                    /** update zi_de_nastere*/
                    if(newStudent.getZi_de_nastere() != null
                            && newStudent.getZi_de_nastere().length() > 0
                            && !foundStudent.getZi_de_nastere().equals(newStudent.getZi_de_nastere()))
                    {
                        foundStudent.setZi_de_nastere(newStudent.getZi_de_nastere());
                        foundStudent.setPassword(DoBandCNPandGenderRandomizer.splitDoBbyDot(newStudent.getZi_de_nastere()));
                    }

                    return studentAccountsDBRepository.save(foundStudent);
                }).
                orElseThrow(
                        () -> new IllegalStateException("student with id " + studentId + " does not exist")
                );
    }
}
