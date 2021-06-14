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

    /* ~~~~~~~~~~~ Delete Student in the Camin Table Corespunzator ~~~~~~~~~~~ */
    public void deleteStudentInCaminLeuA(CaminLeuA selectedStudentCamin)
    {
        caminLeuARepository.deleteByNumePrenumeMyTokenCNP(
                selectedStudentCamin.getMyToken(),
                selectedStudentCamin.getCnp(),
                selectedStudentCamin.getNume(),
                selectedStudentCamin.getPrenume());
    }

    /*  ~~~~~~~~~~~ Update Student from Camin Leu A with FriendToken ~~~~~~~~~~~ */
    @Transactional
    public void updateFriendTokenOfStudentInCaminLeuA(Long studentId, CaminLeuA newStudentCamin)
    {
        caminLeuARepository.findById(studentId)
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

                    return caminLeuARepository.save(foundStudentCamin);
                }).
                orElseThrow(
                        () -> new IllegalStateException("student with id " + studentId + " does not exist")
                );
    }
}
