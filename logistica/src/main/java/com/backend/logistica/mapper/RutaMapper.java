package com.backend.logistica.mapper;

import org.springframework.stereotype.Component;

import com.backend.logistica.entities.Ruta;
import com.backend.logistica.entities.dto.RutaDto;

@Component
public class RutaMapper {
    
    public static Ruta dtoToEntity(RutaDto rutaDto){
        if(rutaDto == null) return null;

        Ruta ruta = Ruta.builder()
            .cantidadDepositos(rutaDto.getCantidadDepositos())
            .cantidadTramos(rutaDto.getCantidadTramos())
            .build();

        return ruta;
    }
}
