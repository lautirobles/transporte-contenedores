package com.backend.logistica.entities.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RutaDto {
    private Long id;
    private Integer cantidadTramos;
    private Integer cantidadDepositos;
}
