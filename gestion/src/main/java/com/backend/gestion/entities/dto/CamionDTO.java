package com.backend.gestion.entities.dto;

import java.math.BigDecimal;

public class CamionDTO {
    Long id;
    BigDecimal costos;  // Costo base de traslado por km
    BigDecimal consumoPromedio; // Consumo de combustible promedio
    int capacidadPeso;
    int capacidadVolumen;
}
