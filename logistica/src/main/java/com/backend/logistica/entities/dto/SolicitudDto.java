package com.backend.logistica.entities.dto;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SolicitudDto {
    private Long numero;
    private ContenedorDto contenedor;
    private ClienteDTO cliente;
    private String estado;
    private Long rutaAsignada;
    private BigDecimal costoEstimado;
    private Integer tiempoEstimado;
    private BigDecimal costoFinal;
    private Integer tiempoReal;

    
    // getClienteId
    public Long getClienteId() {
        return cliente.getId();
    }


    // getContenedorId
    public Long getContenedorId() {
        return contenedor.getId();
    }

    // getPeso
    public BigDecimal getPeso() {
        return contenedor.getPeso();
    }

    // getVolumen
    public BigDecimal getVolumen() {
        return contenedor.getVolumen();
    }




}
