package com.backend.logistica.clients;

import com.backend.logistica.entities.dto.external.DistanciaDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class GeoApiClient {

    // Inyectamos el bean específico por nombre usando @Qualifier
    @Qualifier("geoApiRestClient")
    private final RestClient geoClient;

    public DistanciaDto getDistancia(String origen, String destino) {
        try {
            // La URL base ya está configurada, solo agregamos el path y parámetros
            return geoClient.get()
                .uri(uriBuilder -> uriBuilder
                    .path("/api/distancia")
                    .queryParam("origen", origen)
                    .queryParam("destino", destino)
                    .build())
                .retrieve()
                .body(DistanciaDto.class);
        } catch (Exception e) {
            // Manejo básico de errores: devolvemos un objeto vacío o lanzamos excepción
            // Para simplificar, asumimos que si falla, la distancia es 0 (o podrías lanzar RuntimeException)
            System.err.println("Error conectando con GeoAPI: " + e.getMessage());
            DistanciaDto fallback = new DistanciaDto();
            fallback.setKilometros(0);
            fallback.setDuracionTexto("Desconocido");
            return fallback;
        }
    }
}   