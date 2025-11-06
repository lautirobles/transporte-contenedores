package com.backend.gestion.services.interfaces;
import com.backend.gestion.entities.Tarifa;
import java.util.List;

public interface TarifaService {

    List<Tarifa> getAllTarifas();

    Tarifa createTarifa(Tarifa tarifa);

    Tarifa updateTarifa(Long id, Tarifa tarifa);

    void deleteTarifa(Long id);
    
}
