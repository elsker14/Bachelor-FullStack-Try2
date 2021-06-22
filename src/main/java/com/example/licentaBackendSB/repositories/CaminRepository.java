package com.example.licentaBackendSB.repositories;

import com.example.licentaBackendSB.entities.Camin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaminRepository extends JpaRepository<Camin, Long> {
}
