package com.backend.logistica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backend.logistica.entities.Estadia;

public interface EstadiaRepositoryImpl extends JpaRepository<Estadia, Long>{
    
}
