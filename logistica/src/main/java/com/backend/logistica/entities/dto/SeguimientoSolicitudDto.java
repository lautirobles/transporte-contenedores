package com.backend.logistica.entities.dto;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SeguimientoSolicitudDto {
    private Long numeroSolicitud;
    private String estadoActual;
    private Long contenedorId;
    private BigDecimal costoEstimado;
    private Integer tiempoEstimado;
    // Lista cronológica de cambios
    private List<HistorialEstadoDto> historialEstados;
}