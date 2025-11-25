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
                .uri("/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    // Imprimir el código de estado real
                    System.out.println("ERROR DE CAMION: " + res.getStatusCode()); 
                    
                    if (res.getStatusCode().value() == 404) {
                        throw new NoSuchElementException("No se encontro camion con id: " + id);
                    } else {
                        throw new RuntimeException("Error " + res.getStatusCode() + " al llamar a Gestion");
                    }
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
