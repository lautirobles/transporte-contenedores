package com.backend.logistica.services.interfaces;


import java.util.List;

import com.backend.logistica.entities.Solicitud;
import com.backend.logistica.entities.dto.SolicitudDto;
import com.backend.logistica.entities.dto.SeguimientoSolicitudDto;
import com.backend.logistica.entities.dto.RutaDto;
import com.backend.logistica.entities.dto.UpdateSolicitudDto;


public interface SolicitudService {
    
    List<SolicitudDto> getAllSolicitudes();

    SolicitudDto getSolicitud(Long numero);
    
    SolicitudDto createSolicitud(SolicitudDto solicitudDto);
    
    SolicitudDto updateSolicitud(Long numero, Solicitud solicitud);

    void updateFechaCostoSolicitud(Long numero, UpdateSolicitudDto dto);

    void updateRutaAsignadaSolicitud(Long numero, RutaDto ruta);

    void deleteSolicitud(Long numero);

    SolicitudDto createSolicitudConClienteYContenedor(SolicitudDto dto);

    SeguimientoSolicitudDto obtenerSeguimiento(Long numero);
}
