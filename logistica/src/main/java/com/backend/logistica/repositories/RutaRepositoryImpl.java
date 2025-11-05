package com.backend.logistica.repositories;
import com.backend.logistica.entities.Ruta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RutaRepositoryImpl extends JpaRepository<Ruta, Long> {
    
}
