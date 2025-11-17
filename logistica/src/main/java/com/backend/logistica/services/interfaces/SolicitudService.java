package com.backend.logistica.services.interfaces;


import java.util.List;

import com.backend.logistica.entities.Solicitud;
import com.backend.logistica.entities.dto.RutaDto;
import com.backend.logistica.entities.dto.UpdateSolicitudDto;


public interface SolicitudService {
    
    List<Solicitud> getAllSolicitudes();

    Solicitud getSolicitud(Long numero);
    
    Solicitud createSolicitud(Solicitud solicitud);
    
    Solicitud updateSolicitud(Long numero, Solicitud solicitud);

    Solicitud updateFechaCostoSolicitud(Long numero, UpdateSolicitudDto dto);

    Solicitud updateRutaAsignadaSolicitud(Long numero, RutaDto ruta);

    void deleteSolicitud(Long numero);
}
