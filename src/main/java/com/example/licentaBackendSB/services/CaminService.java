package com.example.licentaBackendSB.services;

import com.example.licentaBackendSB.entities.Camin;
import com.example.licentaBackendSB.repositories.CaminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaminService {
    //Fields
    private final CaminRepository caminRepository;

    //Constructor
    @Autowired
    public CaminService(CaminRepository caminRepository) {
        this.caminRepository = caminRepository;
    }

    //Methods
    public List<Camin> getCamine() {
        return caminRepository.findAll();
    }
}
