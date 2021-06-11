package com.example.licentaBackendSB.services;

import com.example.licentaBackendSB.entities.StudentAccount;
import com.example.licentaBackendSB.others.LoggedAccount;
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

    /*  ~~~~~~~~~~~ Delete Student from Student Table ~~~~~~~~~~~ */
    public void deleteStudent(long studentId) {
        boolean exists = studentAccountsDBRepository.existsById(studentId);
        if(!exists)
        {
            throw new IllegalStateException("StudentAccount with id " + studentId + " doesn't exist");
        }

        studentAccountsDBRepository.deleteById(studentId);
    }
}
