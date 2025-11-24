package com.backend.logistica.mapper;

import java.util.Collections;
import java.util.stream.Collectors;

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

    public static RutaDto entityToDto(Ruta ruta){
        if(ruta == null) return null;

        return new RutaDto(
            ruta.getId(),
            ruta.getCantidadTramos(),
            ruta.getCantidadDepositos(),
            ruta.getTramos() != null ? 
                ruta.getTramos().stream()
                    .map(TramoMapper::entityToDto) 
                    .collect(Collectors.toList()) 
                : Collections.emptyList()
        );
    }
}
