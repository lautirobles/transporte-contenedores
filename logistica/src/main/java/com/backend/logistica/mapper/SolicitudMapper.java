package com.backend.logistica.mapper;

import org.springframework.stereotype.Component;

import com.backend.logistica.entities.dto.ClienteDTO;
import com.backend.logistica.entities.Contenedor;
import com.backend.logistica.entities.dto.ContenedorDto;
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
            .cliente(dto.getClienteId()) // le pondria solo el id
            .estado(dto.getEstado())
            .costoEstimado(dto.getCostoEstimado())
            .tiempoEstimado(dto.getTiempoEstimado())
            .costoFinal(dto.getCostoFinal())
            .tiempoReal(dto.getTiempoReal())
            .build();

        return solicitud;
    }

    // Modificamos el método para que reciba los DTOs que no puede construir por sí mismo.
    public static SolicitudDto entityToDto(Solicitud solicitud, ClienteDTO clienteDTO, Contenedor contenedor){
        if(solicitud == null) return null;

        // Usamos el ContenedorMapper para convertir la entidad Contenedor a su DTO.
        ContenedorDto contenedorDto = ContenedorMapper.entityToDto(contenedor);

        return new SolicitudDto(
            solicitud.getNumero(),
            contenedorDto, // Pasamos el DTO del contenedor
            clienteDTO,    // Pasamos el DTO del cliente que recibimos como parámetro
            solicitud.getEstado(),
            solicitud.getRutaAsignada() != null ? solicitud.getRutaAsignada().getId() : null,
            solicitud.getCostoEstimado(),
            solicitud.getTiempoEstimado(),
            solicitud.getCostoFinal(),
            solicitud.getTiempoReal()
        );

    }
}
