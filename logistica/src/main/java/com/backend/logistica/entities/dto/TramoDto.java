package com.backend.logistica.entities.dto;

import java.time.LocalDateTime;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TramoDto {
    private Long idTramo;
    private String origen;
    private String destino;
    private String tipo;
    private String estado;
    private Double costoAproximado;
    private Double costoReal;
    private LocalDateTime fechaHoraInicioEstimada;
    private LocalDateTime fechaHoraFinEstimada;
    private LocalDateTime fechaHoraInicioReal;
    private LocalDateTime fechaHoraFinReal;
    private Long camion;
    private Long ruta;
}
