package com.backend.logistica.mapper;

import org.springframework.stereotype.Component;

import com.backend.logistica.entities.Tramo;
import com.backend.logistica.entities.dto.TramoDto;

@Component
public class TramoMapper {
    
    public static Tramo dtoToEntity(TramoDto tramoDto){
        if(tramoDto == null) return null;

        Tramo tramo = Tramo.builder()
            .idTramo(tramoDto.getIdTramo())
            .origen(tramoDto.getOrigen())
            .destino(tramoDto.getDestino())
            .tipo(tramoDto.getTipo())
            .estado(tramoDto.getEstado())
            .costoAproximado(tramoDto.getCostoAproximado())
            .costoReal(tramoDto.getCostoReal())
            .fechaHoraInicioEstimada(tramoDto.getFechaHoraInicioEstimada())
            .fechaHoraFinEstimada(tramoDto.getFechaHoraFinEstimada())
            .fechaHoraInicioReal(tramoDto.getFechaHoraInicioReal())
            .fechaHoraFinReal(tramoDto.getFechaHoraFinReal())
            .camion(tramoDto.getCamion())
            .ruta(null)  // No se mapea desde el DTO, se asigna en el servicio
            .build();
        
        return tramo;
    }

    public static TramoDto entityToDto(Tramo tramo){
        if(tramo == null) return null;

        return new TramoDto(
            tramo.getIdTramo(),
            tramo.getOrigen(),
            tramo.getDestino(),
            tramo.getTipo(),
            tramo.getEstado(),
            tramo.getCostoAproximado(),
            tramo.getCostoReal(),
            tramo.getFechaHoraInicioEstimada(),
            tramo.getFechaHoraFinEstimada(),
            tramo.getFechaHoraInicioReal(),
            tramo.getFechaHoraFinReal(),
            tramo.getCamion(),
            tramo.getRuta() != null ? tramo.getRuta().getId() : null
        );
    }
}
