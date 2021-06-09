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

    //Metoda care sa preia toate conturile de studenti din DB si il intoarce pe cel logat in sesiunea curenta <3
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
}
