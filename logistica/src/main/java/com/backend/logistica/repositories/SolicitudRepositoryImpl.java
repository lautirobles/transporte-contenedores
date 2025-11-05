package com.backend.logistica.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.logistica.entities.Solicitud;

public interface SolicitudRepositoryImpl extends JpaRepository<Solicitud, Long> {
    
}
