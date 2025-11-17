package com.backend.logistica.entities.dto;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSolicitudDto {
    private BigDecimal costoFinal;
    private Integer tiempoReal;
}
