package com.backend.logistica.entities.dto.external;
import lombok.Data;

@Data
public class DistanciaDto {
    private String origen;
    private String destino;
    private double kilometros;
    private String duracionTexto; // Ej: "1 hour 30 mins"
}