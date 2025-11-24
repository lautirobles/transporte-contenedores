package com.backend.logistica.entities.dto;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDto {
    private Long numero;
    private Long contenedor;
    private Long cliente;
    private String estado;
    private Long rutaAsignada;
    private BigDecimal costoEstimado;
    private Integer tiempoEstimado;
    private BigDecimal costoFinal;
    private Integer tiempoReal;

    
    // getClienteId
    public Long getClienteId() {
        return cliente;
    }


    // getContenedorId
    public Long getContenedorId() {
        return contenedor;
    }

}
