package com.example.licentaBackendSB.services;

import com.example.licentaBackendSB.entities.CaminLeuA;
import com.example.licentaBackendSB.entities.CaminLeuC;
import com.example.licentaBackendSB.entities.CaminP20;
import com.example.licentaBackendSB.entities.CaminP23;
import com.example.licentaBackendSB.repositories.CaminP20Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CaminP20Service {

    //Fields
    private final CaminP20Repository caminP20Repository;

    //Constructor
    @Autowired
    public CaminP20Service(CaminP20Repository caminP20Repository) {
        this.caminP20Repository = caminP20Repository;
    }

    //Methods
    /*  ~~~~~~~~~~~ Get List of P20 Students ~~~~~~~~~~~ */
    public List<CaminP20> getP20Students()
    {
        //select * from caminP20 (query in DB)
        List <CaminP20> caminP20List = caminP20Repository.findAll();

        return caminP20List;
    }

    /*  ~~~~~~~~~~~ Introduce Student in the Camin Table Corespunzator ~~~~~~~~~~~ */
    public void introduceNewStudentCaminP20(CaminP20 newStudentCamin)
    {
        caminP20Repository.save(newStudentCamin);
    }

    /* ~~~~~~~~~~~ Delete Student in the Camin Table Corespunzator ~~~~~~~~~~~ */
    public void deleteStudentInCaminP20(CaminP20 selectedStudentCamin)
    {
        caminP20Repository.deleteByNumePrenumeMyTokenCNP(
                selectedStudentCamin.getMyToken(),
                selectedStudentCamin.getCnp(),
                selectedStudentCamin.getNume(),
                selectedStudentCamin.getPrenume());
    }

    /*  ~~~~~~~~~~~ Update Student from Camin P20 with FriendToken ~~~~~~~~~~~ */
    @Transactional
    public void updateFriendTokenOfStudentInCaminP20(CaminP20 studentCamin)
    {
        Optional<CaminP20> studentDinCaminP20 = caminP20Repository.getStudentFromCamin(studentCamin.getCnp());
        studentDinCaminP20.get().setFriendToken(studentCamin.getFriendToken());
        studentDinCaminP20.ifPresent(caminP20 -> caminP20Repository.updateFriendTokenFromStudentInCamin(caminP20.getFriendToken(), caminP20.getCnp()));
    }
}
