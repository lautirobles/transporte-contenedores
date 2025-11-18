package com.backend.logistica.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.backend.logistica.entities.Contenedor;


public interface ContenedorRepositoryImpl extends JpaRepository<Contenedor, Long>{
    
}
