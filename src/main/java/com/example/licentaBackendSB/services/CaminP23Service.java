package com.example.licentaBackendSB.services;

import com.example.licentaBackendSB.entities.CaminLeuA;
import com.example.licentaBackendSB.entities.CaminLeuC;
import com.example.licentaBackendSB.entities.CaminP20;
import com.example.licentaBackendSB.entities.CaminP23;
import com.example.licentaBackendSB.repositories.CaminP23Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CaminP23Service {

    //Fields
    private final CaminP23Repository caminP23Repository;

    //Constructor
    @Autowired
    public CaminP23Service(CaminP23Repository caminP23Repository) {
        this.caminP23Repository = caminP23Repository;
    }

    //Methods
    /*  ~~~~~~~~~~~ Get List of P23 Students ~~~~~~~~~~~ */
    public List<CaminP23> getP23Students()
    {
        //select * from caminP23 (query in DB)
        List <CaminP23> caminP23List = caminP23Repository.findAll();

        return caminP23List;
    }

    /*  ~~~~~~~~~~~ Introduce Student in the Camin Table Corespunzator ~~~~~~~~~~~ */
    public void introduceNewStudentCaminP23(CaminP23 newStudentCamin)
    {
        caminP23Repository.save(newStudentCamin);
    }

    /* ~~~~~~~~~~~ Delete Student in the Camin Table Corespunzator ~~~~~~~~~~~ */
    public void deleteStudentInCaminP23(CaminP23 selectedStudentCamin)
    {
//        caminP23Repository.deleteById(selectedStudentCamin.getId());
        caminP23Repository.deleteByNumePrenumeMyTokenCNP(
                selectedStudentCamin.getMyToken(),
                selectedStudentCamin.getCnp(),
                selectedStudentCamin.getNume(),
                selectedStudentCamin.getPrenume());
    }

    /*  ~~~~~~~~~~~ Update Student from Camin P23 with FriendToken ~~~~~~~~~~~ */
    @Transactional
    public void updateFriendTokenOfStudentInCaminP23(Long studentId, CaminP23 newStudentCamin)
    {
        caminP23Repository.findById(studentId)
                .map(foundStudentCamin -> {
                    //Validari si Verificari

                    /** update student with friendtoken in camin table*/
                    if(newStudentCamin.getFriendToken() != null
                            && newStudentCamin.getFriendToken().length() > 0
                            && !foundStudentCamin.getFriendToken().equals(newStudentCamin.getFriendToken())
                            && !foundStudentCamin.getMyToken().equals(newStudentCamin.getFriendToken()))
                    {
                        foundStudentCamin.setFriendToken(newStudentCamin.getFriendToken());
                    }

                    return caminP23Repository.save(foundStudentCamin);
                }).
                orElseThrow(
                        () -> new IllegalStateException("student with id " + studentId + " does not exist")
                );
    }
}
