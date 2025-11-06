package com.backend.gestion.services;

import org.springframework.stereotype.Service;
import com.backend.gestion.services.interfaces.TarifaService;
import com.backend.gestion.entities.Tarifa;
import com.backend.gestion.repositories.TarifaRepository;
import java.util.List;

@Service
public class TarifaServiceImpl implements TarifaService{
    
    private final TarifaRepository tarifaRepository;

    public TarifaServiceImpl(TarifaRepository tarifaRepository) {
        this.tarifaRepository = tarifaRepository;
    }

    public List<Tarifa> getAllTarifas() {
        return tarifaRepository.findAll();
    }

    public Tarifa createTarifa(Tarifa tarifa) {
        return tarifaRepository.save(tarifa);
    }

    public Tarifa updateTarifa(Long id, Tarifa tarifa) {
        if (tarifaRepository.existsById(id)) {
            tarifa.setId(id);
            return tarifaRepository.save(tarifa);
        }
        return null;
    }

    public void deleteTarifa(Long id) {
        tarifaRepository.deleteById(id);
    }

}
