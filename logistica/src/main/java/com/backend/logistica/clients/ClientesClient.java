package com.backend.logistica.clients;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatusCode;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import com.backend.logistica.entities.dto.ClienteDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor  //Inyecta el RestClient configurado en RestClientConfig
public class ClientesClient {

    private final RestClient clientesClient;

    //restclient obtener todos los clientes y validar existencia por id

    public ClienteDTO obtenerPorId(Long id) {
        try {

            return clientesClient.get()
                .uri("/api/v1/clientes/{id}" + id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    throw new NoSuchElementException("No se encontro cliente con id: " + id);
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new RuntimeException("Error del servidor al obtener cliente con id: " + id);
                })
                .body(ClienteDTO.class);
        } catch (RestClientResponseException ex) {
            throw new RuntimeException("Error al comunicarse con el servicio de clientes: " + ex.getMessage());
        }
    }

    // restclient para crear cliente
    public ClienteDTO crear(ClienteDTO cliente) {
        try {
            return clientesClient.post()
                .uri("/api/v1/clientes")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    throw new RuntimeException("Error al crear cliente: " + cliente);
                })
                .onStatus(HttpStatusCode::is5xxServerError, (req, res) -> {
                    throw new RuntimeException("Error del servidor al crear cliente: " + cliente);
                })
                .body(ClienteDTO.class);
        } catch (RestClientResponseException ex) {
            throw new RuntimeException("Error al comunicarse con el servicio de clientes: " + ex.getMessage());
        }
    }



    
}
