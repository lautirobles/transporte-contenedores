// package com.backend.apigateway.config;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.cloud.gateway.route.RouteLocator;
// import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class GwConfig {

//     @Bean
//     public RouteLocator configurarRutas(RouteLocatorBuilder builder ,
//                                             @Value("${tpi-backend.url-microservicio-logistica}") String uriLogistica,
//                                             @Value("${tpi-backend.url-microservicio-gestion}") String uriGestion){
//         return builder.routes()
//                 .route(route -> route.path("/api/v1/logistica/**")
//                         .filters(f -> f.addRequestHeader("X-Internal-Key", "CLAVE_INTERNA"))
//                         .uri(uriLogistica))

//                 .route(route -> route.path("/api/v1/gestion/**")
//                         .filters(f -> f.addRequestHeader("X-Internal-Key", "CLAVE_INTERNA"))
//                         .uri(uriGestion))
//                 .build();
//     }

// }
