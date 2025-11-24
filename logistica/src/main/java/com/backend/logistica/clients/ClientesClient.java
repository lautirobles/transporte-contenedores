package com.backend.logistica.clients;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientResponseException;

import com.backend.logistica.entities.dto.ClienteDTO;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor  //Inyecta el RestClient configurado en RestClientConfig
@Service
public class ClientesClient {

    private final RestClient clientesRestClient;

    //restclient obtener todos los clientes y validar existencia por id

    public ClienteDTO obtenerPorId(Long id) {
        try {

            return clientesRestClient.get()
                .uri("/{id}", id)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, (req, res) -> {
                    // Imprimir el código de estado real
                    System.out.println("ERROR DE CLIENTE: " + res.getStatusCode()); 
                    
                    if (res.getStatusCode().value() == 404) {
                        throw new NoSuchElementException("No se encontro cliente con id: " + id);
                    } else {
                        throw new RuntimeException("Error " + res.getStatusCode() + " al llamar a Gestion");
                    }
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
            return clientesRestClient.post()
                .uri("")
                .body(cliente)
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
