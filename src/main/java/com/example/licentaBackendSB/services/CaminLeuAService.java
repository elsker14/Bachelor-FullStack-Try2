package com.example.licentaBackendSB.services;

import com.example.licentaBackendSB.entities.CaminLeuA;
import com.example.licentaBackendSB.entities.Student;
import com.example.licentaBackendSB.repositories.CaminLeuARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CaminLeuAService {

    //Fields
    private final CaminLeuARepository caminLeuARepository;

    //Constructor
    @Autowired
    public CaminLeuAService(CaminLeuARepository caminLeuARepository) {
        this.caminLeuARepository = caminLeuARepository;
    }

    //Methods
    /*  ~~~~~~~~~~~ Get List of Leu A Students ~~~~~~~~~~~ */
    public List<CaminLeuA> getLeuAStudents()
    {
        //select * from caminLeuA (query in DB)
        List <CaminLeuA> caminLeuAList = caminLeuARepository.findAll();

        return caminLeuAList;
    }

    /*  ~~~~~~~~~~~ Introduce Student in the Camin Table Corespunzator ~~~~~~~~~~~ */
    public void introduceNewStudentCaminLeuA(CaminLeuA newStudentCamin)
    {
        caminLeuARepository.save(newStudentCamin);
    }
}
