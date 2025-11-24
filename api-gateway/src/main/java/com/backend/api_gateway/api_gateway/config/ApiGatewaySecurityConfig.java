package com.backend.api_gateway.api_gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class ApiGatewaySecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .csrf(ServerHttpSecurity.CsrfSpec::disable)
            .authorizeExchange(exchanges -> exchanges
                // Los endpoints que no están cubiertos por las reglas de los microservicios
                // deben estar autenticados, excepto los públicos (como la documentación).
                .pathMatchers("/actuator/**").permitAll() // Generalmente permitimos acceso al health check
                .anyExchange().permitAll() // Permitimos todo para pruebas
            );

        return http.build();
    }
}