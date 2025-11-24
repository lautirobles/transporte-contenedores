package com.backend.logistica.entities.dto;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HistorialEstadoDto {
    private String estadoAnterior;
    private String estadoNuevo;
    private LocalDateTime fechaCambio;
}