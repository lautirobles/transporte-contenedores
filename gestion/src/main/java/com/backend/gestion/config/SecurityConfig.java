package com.backend.gestion.config; // o com.backend.gestion.config

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
                // Solo los usuarios con rol OPERADOR pueden acceder a los endpoints de gestión
                // .requestMatchers("/api/gestion/**").hasRole("OPERADOR")
                // Cualquier otra solicitud (como /actuator) debe estar autenticada, pero sin un rol específico
                .anyRequest().permitAll()
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
