package com.backend.logistica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.logistica.entities.CambioEstado; 

@Repository
public interface CambioEstadoRepositoryImpl extends JpaRepository<CambioEstado, Long> {
    
}
