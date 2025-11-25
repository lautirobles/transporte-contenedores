package com.backend.gestion.config; // o com.backend.gestion.config

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
// import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.http.HttpMethod;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    // @Bean
    // public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    //     http
    //         .authorizeHttpRequests(authorize -> authorize
    //             // Permite el acceso a la consola H2 y swagger sin autenticación
    //             .requestMatchers("/h2-console/**").permitAll()
    //             .requestMatchers("/v3/api-docs","/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()


    //             // Solo los usuarios con rol OPERADOR pueden acceder a los endpoints de gestión
    //             .requestMatchers("/api/v1/gestion/**").hasRole("OPERADOR")
    //             // Cualquier otra solicitud (como /actuator) debe estar autenticada, pero sin un rol específico
    //             .anyRequest().authenticated()
    //         )


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorize -> authorize
                // Acceso público
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**").permitAll()
                
                // --- CORRECCIÓN CRÍTICA ---
                // Permitir a CLIENTE y OPERADOR acceder a la gestión de clientes
                .requestMatchers(HttpMethod.POST, "/api/v1/gestion/clientes").hasAnyRole("OPERADOR", "CLIENTE")
                .requestMatchers(HttpMethod.GET, "/api/v1/gestion/clientes/**").hasAnyRole("OPERADOR", "CLIENTE")
                
                // El resto de la API de gestión sigue protegida solo para OPERADOR (Camiones, Tarifas, etc.)
                .requestMatchers("/api/v1/gestion/**").hasRole("OPERADOR")
                
                .anyRequest().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()))
            );
        
        http.csrf(csrf -> csrf.disable());
        http.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()));

        return http.build();
    }


    @Bean
    public Converter<Jwt, AbstractAuthenticationToken> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRealmRoleConverter());
        return jwtConverter;
    }

}


// Esta clase extrae los roles de "realm_access" -> "roles"
// Agrega este import para logs
class KeycloakRealmRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
    
    private final Logger logger = LoggerFactory.getLogger(KeycloakRealmRoleConverter.class);

    @Override
    public Collection<GrantedAuthority> convert(Jwt jwt) {
        // Debug: Ver qué está llegando
        logger.info("--- PROCESANDO JWT ---");
        
        final Map<String, Object> realmAccess = (Map<String, Object>) jwt.getClaims().get("realm_access");

        
        if (realmAccess == null || realmAccess.isEmpty()) {
            logger.warn("El token NO tiene 'realm_access'");
            return Set.of();
        }

        @SuppressWarnings("unchecked")
        final Collection<String> roles = (Collection<String>) realmAccess.get("roles");

        if (roles == null || roles.isEmpty()) {
            logger.warn("El token tiene 'realm_access' pero NO tiene lista de 'roles'");
            return Set.of();
        }

        logger.info("Roles encontrados en Keycloak: " + roles);

        // Convertir
        return roles.stream()
                .map(roleName -> {
                    String role = "ROLE_" + roleName.toUpperCase();
                    logger.info("Rol transformado: " + role);
                    return new SimpleGrantedAuthority(role);
                })
                .collect(Collectors.toSet());
    }
}