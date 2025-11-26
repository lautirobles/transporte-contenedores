package com.backend.logistica.entities.dto.external;
import java.math.BigDecimal;
import lombok.Data;

@Data
public class CamionDto {
    private Long id;
    private BigDecimal costos; // Costo base por km
    private BigDecimal consumoPromedio;
}