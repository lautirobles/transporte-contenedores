package com.backend.logistica.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.http.HttpHeaders;

@Configuration
public class RestClientConfig {
    @Bean("clientesRestClient")
    RestClient clientesRestClient(@Value("${clients.clientes.url}") String baseUrl) { // construyo un cliente RestClient para el servicio de clientes
        return RestClient.builder() // me lo deja en el contenedor de spring
                .baseUrl(baseUrl) 
                .requestInterceptor((request, body, execution) -> {
                    // 1. Buscamos quién está logueado en este momento
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                    // 2. Si es un usuario autenticado con JWT
                    if (authentication instanceof JwtAuthenticationToken jwtAuth) {
                        // 3. Extraemos el token "crudo"
                        String tokenValue = jwtAuth.getToken().getTokenValue();
                        // 4. Lo inyectamos en el header Authorization
                        request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + tokenValue);
                    }
                    
                    // 5. Continuamos con la petición
                    return execution.execute(request, body);
                })
                .build();
    }

    @Bean("camionesRestClient")
    RestClient camionesRestClient(@Value("${clients.camiones.url}") String baseUrl){
        return RestClient.builder()
                .baseUrl(baseUrl)
                .requestInterceptor((request, body, execution) -> {
                    // 1. Buscamos quién está logueado en este momento
                    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

                    // 2. Si es un usuario autenticado con JWT
                    if (authentication instanceof JwtAuthenticationToken jwtAuth) {
                        // 3. Extraemos el token "crudo"
                        String tokenValue = jwtAuth.getToken().getTokenValue();
                        // 4. Lo inyectamos en el header Authorization
                        request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + tokenValue);
                    }
                    
                    // 5. Continuamos con la petición
                    return execution.execute(request, body);
                })
                .build();
    }
}
