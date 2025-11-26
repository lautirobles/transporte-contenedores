package com.backend.gestion.entities.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TarifasVigentesDto { 
    private BigDecimal precioLitroCombustible;
    private BigDecimal cargoGestionFijo;
    private BigDecimal costoEstadiaDiario;
    // Costos base por km según rango (podrías hacerlo más complejo, pero empecemos simple)
    private BigDecimal costoBaseKmMuyBajo; // Para volumen < 25
    private BigDecimal costoBaseKmAlto;    // Para volumen >= 25
}