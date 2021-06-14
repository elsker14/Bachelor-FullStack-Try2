package com.example.licentaBackendSB.repositories;

import com.example.licentaBackendSB.entities.CaminLeuA;
import com.example.licentaBackendSB.entities.CaminP23;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CaminP23Repository extends JpaRepository<CaminP23, Long> {

    //Update friendToken knowing CNP
    @Transactional
    @Modifying
    @Query("update CaminP23 set friendToken = ?1 where cnp = ?2")
    void updateFriendTokenFromStudentInCamin(String friendToken, String cnp);

    //Get Student knowing CNP
    @Query("select s from CaminP23 s where s.cnp = ?1")
    Optional<CaminP23> getStudentFromCamin(String cnp);

    //Delete student din tabelul de camin care are anumite fielduri identice
    @Transactional
    @Modifying
    @Query("delete from CaminP23 where myToken = ?1 and cnp = ?2 and nume = ?3 and prenume = ?4")
    void deleteByNumePrenumeMyTokenCNP(String studentToken, String studentCNP, String studetNume, String studentPrenume);
}
