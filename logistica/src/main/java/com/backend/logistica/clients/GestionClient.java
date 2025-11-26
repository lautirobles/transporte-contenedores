package com.backend.logistica.clients;

import com.backend.logistica.entities.dto.external.CamionDto;
import com.backend.logistica.entities.dto.external.TarifasVigentesDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class GestionClient {

    @Qualifier("gestionRestClient")
    private final RestClient gestionClient;

    /**
     * Obtiene todas las tarifas vigentes (Combustible, Gestión, Estadía, etc.)
     */
    public TarifasVigentesDto getTarifasVigentes() {
        return gestionClient.get()
            .uri("/api/v1/gestion/tarifa") // Necesitarás crear este endpoint en Gestión
            .retrieve()
            .body(TarifasVigentesDto.class);
    }

    /**
     * Obtiene los datos de un camión específico (Costo y Consumo)
     */
    public CamionDto getCamion(Long id) {
        return gestionClient.get()
            .uri("/api/v1/gestion/camion/" + id) // Necesitarás crear este endpoint en Gestión (findById)
            .retrieve()
            .body(CamionDto.class);
    }
}