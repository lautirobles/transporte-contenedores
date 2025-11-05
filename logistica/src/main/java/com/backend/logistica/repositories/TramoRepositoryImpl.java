package com.backend.logistica.repositories;
import com.backend.logistica.entities.Tramo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TramoRepositoryImpl extends JpaRepository<Tramo, Long> {
    
}
