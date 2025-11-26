package com.backend.logistica.entities.dto.external;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class TarifasVigentesDto {
    private BigDecimal precioLitroCombustible;
    private BigDecimal cargoGestionFijo;
    private BigDecimal costoEstadiaDiaria;

    // --- AGREGAR ESTOS CAMPOS FALTANTES ---
    private BigDecimal costoBaseKmMuyBajo; 
    private BigDecimal costoBaseKmAlto;    
}