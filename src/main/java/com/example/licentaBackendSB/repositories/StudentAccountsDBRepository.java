package com.example.licentaBackendSB.repositories;

import com.example.licentaBackendSB.entities.StudentAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentAccountsDBRepository extends JpaRepository<StudentAccount, Long> {

}
