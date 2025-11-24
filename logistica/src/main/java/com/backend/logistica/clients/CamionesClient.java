package com.backend.logistica.clients;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import com.backend.logistica.entities.dto.CamionDto;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CamionesClient {

    private final RestClient camionesRestClient;

    public CamionDto obtenerPorId(Long id){
        try{
            return camionesRestClient.get()
                .uri("/api/v1/gestion/camion/{id}" + id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    throw new NoSuchElementException("No se encontro cliente con id: " + id);
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new RuntimeException("Error del servidor al obtener cliente con id: " + id);
                })
                .body(CamionDto.class);
        }catch(RestClientResponseException ex){
            throw new RuntimeException("Error al comunicarse con el servicio de camiones: " + ex.getMessage());
        }
    }
}
