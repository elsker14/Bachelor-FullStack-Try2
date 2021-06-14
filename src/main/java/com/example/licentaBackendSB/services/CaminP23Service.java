package com.example.licentaBackendSB.services;

import com.example.licentaBackendSB.entities.CaminLeuA;
import com.example.licentaBackendSB.entities.CaminP23;
import com.example.licentaBackendSB.repositories.CaminP23Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
