package com.backend.logistica.mapper;

import org.springframework.stereotype.Component;

import com.backend.logistica.entities.Solicitud;
import com.backend.logistica.entities.dto.SolicitudDto;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SolicitudMapper {


    public static Solicitud dtoToEntity(SolicitudDto dto){
        if(dto == null) return null;

        Solicitud solicitud = Solicitud.builder()
            .numero(dto.getNumero())
            .cliente(dto.getCliente())
            .estado(dto.getEstado())
            .costoEstimado(dto.getCostoEstimado())
            .tiempoEstimado(dto.getTiempoEstimado())
            .costoFinal(dto.getCostoFinal())
            .tiempoReal(dto.getTiempoReal())
            .build();

        return solicitud;
    }

    public static SolicitudDto entityToDto(Solicitud solicitud){
        if(solicitud == null) return null;

        return new SolicitudDto(
            solicitud.getNumero(),
            solicitud.getContenedor().getId(),
            solicitud.getCliente(),
            solicitud.getEstado(),
            solicitud.getRutaAsignada() != null ? solicitud.getRutaAsignada().getId() : null,
            solicitud.getCostoEstimado(),
            solicitud.getTiempoEstimado(),
            solicitud.getCostoFinal(),
            solicitud.getTiempoReal()
        );

    }
}
