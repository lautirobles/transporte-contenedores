package com.backend.logistica.repositories;
import com.backend.logistica.entities.Tramo;
import com.backend.logistica.entities.Ruta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TramoRepositoryImpl extends JpaRepository<Tramo, Long> {
    
    List<Tramo> findByRuta(Ruta ruta);
}
