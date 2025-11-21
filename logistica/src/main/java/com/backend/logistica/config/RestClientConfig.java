package com.backend.logistica.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Value;


@Configuration
public class RestClientConfig {
    @Bean
    RestClient clientesClient(@Value("${clients.clientes.url}") String baseUrl) { // construyo un cliente RestClient para el servicio de clientes
        return RestClient.builder() // me lo deja en el contenedor de spring
                .baseUrl(baseUrl) // lo inyecta en el baseUrl
                .build();
    }
}
