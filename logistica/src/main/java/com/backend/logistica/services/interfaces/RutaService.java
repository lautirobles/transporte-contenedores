package com.backend.logistica.services.interfaces;

import java.util.List;
import com.backend.logistica.entities.Ruta;
import com.backend.logistica.entities.dto.RutaDto;

public interface RutaService {

    List<Ruta> getAllRutas();

    Ruta getRuta(Long id);
    
    List<RutaDto> getRutasAlternativas(String origen, String destino);
}
