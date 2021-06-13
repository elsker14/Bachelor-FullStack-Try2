package com.example.licentaBackendSB.services;

import com.example.licentaBackendSB.entities.CaminP20;
import com.example.licentaBackendSB.repositories.CaminP20Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
}
