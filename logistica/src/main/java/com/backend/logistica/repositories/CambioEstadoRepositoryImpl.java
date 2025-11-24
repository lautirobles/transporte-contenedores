package com.backend.logistica.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.backend.logistica.entities.CambioEstado;
import com.backend.logistica.entities.Contenedor;

import java.util.List; 

@Repository
public interface CambioEstadoRepositoryImpl extends JpaRepository<CambioEstado, Long> {

    // Busca por contenedor y ordena por fecha (descendente para ver lo más reciente primero)
    List<CambioEstado> findByContenedorOrderByFechaCambioDesc(Contenedor contenedor);
    
}
