package com.backend.logistica.services;
import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.logistica.entities.Ruta;
import com.backend.logistica.repositories.RutaRepositoryImpl;
import com.backend.logistica.services.interfaces.RutaService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RutaServiceImpl implements RutaService {
    
    private final RutaRepositoryImpl rutaRepositoryImpl;

    @Override
    public List<Ruta> getAllRutas(){
        return rutaRepositoryImpl.findAll();
    }

    @Override
    public Ruta getRuta(Long id){
        return rutaRepositoryImpl.findById(id).orElse(null);
    }
}
