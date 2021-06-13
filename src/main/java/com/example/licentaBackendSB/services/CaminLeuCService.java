package com.example.licentaBackendSB.services;

import com.example.licentaBackendSB.entities.CaminLeuC;
import com.example.licentaBackendSB.repositories.CaminLeuCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaminLeuCService {

    //Fields
    private final CaminLeuCRepository caminLeuCRepository;

    //Constructor
    @Autowired
    public CaminLeuCService(CaminLeuCRepository caminLeuCRepository) {
        this.caminLeuCRepository = caminLeuCRepository;
    }

    //Methods
    /*  ~~~~~~~~~~~ Get List of Leu C Students ~~~~~~~~~~~ */
    public List<CaminLeuC> getLeuCStudents()
    {
        //select * from caminLeuC (query in DB)
        List <CaminLeuC> caminLeuCList = caminLeuCRepository.findAll();

        return caminLeuCList;
    }
}
