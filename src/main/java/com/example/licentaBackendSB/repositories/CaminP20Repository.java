package com.example.licentaBackendSB.repositories;

import com.example.licentaBackendSB.entities.CaminLeuA;
import com.example.licentaBackendSB.entities.CaminP20;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface CaminP20Repository extends JpaRepository<CaminP20, Long> {

    //Delete student din tabelul de camin care are anumite fielduri identice
    @Transactional
    @Modifying
    @Query("delete from CaminP20 where myToken = ?1 and cnp = ?2 and nume = ?3 and prenume = ?4")
    void deleteByNumePrenumeMyTokenCNP(String studentToken, String studentCNP, String studetNume, String studentPrenume);
}
