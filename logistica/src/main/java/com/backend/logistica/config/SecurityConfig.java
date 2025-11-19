package com.backend.logistica.config; // o com.backend.gestion.config

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // Permite el acceso a la consola H2 sin autenticación
                .requestMatchers(HttpMethod.GET, "/h2-console/**").permitAll()
                // Un CLIENTE puede crear una solicitud
                .requestMatchers(HttpMethod.POST, "/api/logistica/solicitudes").hasRole("CLIENTE")
                // Un OPERADOR puede ver todas las solicitudes
                .requestMatchers(HttpMethod.GET, "/api/logistica/solicitudes/**").hasRole("OPERADOR")
                // Un TRANSPORTISTA puede actualizar una solicitud
                .requestMatchers(HttpMethod.PATCH, "/api/logistica/solicitudes/**").hasRole("TRANSPORTISTA")
                // Cualquier otra solicitud (como /actuator) debe estar autenticada
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> {})
            );
        
        // Necesario para que la consola H2 funcione correctamente
        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }
}
