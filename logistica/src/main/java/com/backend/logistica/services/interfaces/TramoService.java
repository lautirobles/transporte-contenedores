package com.backend.logistica.services.interfaces;

import java.util.List;


import com.backend.logistica.entities.dto.RutaDto;
import com.backend.logistica.entities.dto.TramoDto;


public interface TramoService {
    
    List<TramoDto> getTramosPorRuta(RutaDto ruta);

    void updateEstadoTramo(Long id, String estado);

    void updateCamion(Long id, Long idCamion);

}
