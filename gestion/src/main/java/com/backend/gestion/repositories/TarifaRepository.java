package com.backend.gestion.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.gestion.entities.Tarifa;

@Repository
public interface TarifaRepository extends JpaRepository<Tarifa, Long> {

    
}



