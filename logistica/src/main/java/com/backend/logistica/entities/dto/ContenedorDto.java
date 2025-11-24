package com.backend.logistica.entities.dto;

import java.math.BigDecimal;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ContenedorDto {
    private Long id;

    private BigDecimal peso;
    private BigDecimal volumen;
    private String estado;

    private Long clienteAsociado;

    // getId
    public Long getId() {
        return id;
    }
}
