package com.example.licentaBackendSB.services;

import com.example.licentaBackendSB.entities.CaminLeuA;
import com.example.licentaBackendSB.entities.CaminLeuC;
import com.example.licentaBackendSB.repositories.CaminLeuCRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

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

    /*  ~~~~~~~~~~~ Introduce Student in the Camin Table Corespunzator ~~~~~~~~~~~ */
    public void introduceNewStudentCaminLeuC(CaminLeuC newStudentCamin)
    {
        caminLeuCRepository.save(newStudentCamin);
    }

    /* ~~~~~~~~~~~ Delete Student in the Camin Table Corespunzator ~~~~~~~~~~~ */
    public void deleteStudentInCaminLeuC(CaminLeuC selectedStudentCamin)
    {
//        caminLeuCRepository.deleteById(selectedStudentCamin.getId());
        caminLeuCRepository.deleteByNumePrenumeMyTokenCNP(
                selectedStudentCamin.getMyToken(),
                selectedStudentCamin.getCnp(),
                selectedStudentCamin.getNume(),
                selectedStudentCamin.getPrenume());
    }

    /*  ~~~~~~~~~~~ Update Student from Camin Leu C with FriendToken ~~~~~~~~~~~ */
    @Transactional
    public void updateFriendTokenOfStudentInCaminLeuC(CaminLeuC studentCamin)
    {
        Optional<CaminLeuC> studentDinCaminLeuC = caminLeuCRepository.getStudentFromCamin(studentCamin.getCnp());
        studentDinCaminLeuC.get().setFriendToken(studentCamin.getFriendToken());
        studentDinCaminLeuC.ifPresent(caminLeuC -> caminLeuCRepository.updateFriendTokenFromStudentInCamin(caminLeuC.getFriendToken(), caminLeuC.getCnp()));
    }
}
