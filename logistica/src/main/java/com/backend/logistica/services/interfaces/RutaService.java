package com.backend.logistica.services.interfaces;

import java.util.List;
import com.backend.logistica.entities.Ruta;

public interface RutaService {

    List<Ruta> getAllRutas();

    Ruta getRuta(Long id);
    
}
