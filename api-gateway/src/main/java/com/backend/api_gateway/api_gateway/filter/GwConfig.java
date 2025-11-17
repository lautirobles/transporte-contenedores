// package com.Agencia.Gateway.Config;

// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.cloud.gateway.route.RouteLocator;
// import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;

// @Configuration
// public class GwConfig {

//     @Bean
//     public RouteLocator routesconfiguration(RouteLocatorBuilder builder ,
//                                             @Value("${url.pruebas}") String urlPruebas,
//                                             @Value("${url.notificaciones}") String urlNotificaciones,
//                                             @Value("${url.reportes}") String urlReportes){
//         return builder
//                 .routes()
//                 .route(route -> route.path("/api/pruebas/**")
//                         .filters(f -> f.addRequestHeader("X-Internal-Key", "CLAVE_INTERNA"))
//                         .uri(urlPruebas))

//                 .route(route -> route.path("/api/notificaciones/**")
//                         .filters(f -> f.addRequestHeader("X-Internal-Key", "CLAVE_INTERNA"))
//                         .uri(urlNotificaciones))
//                 .route(route -> route.path("/api/reportes/**")
//                         .filters(f -> f.addRequestHeader("X-Internal-Key", "CLAVE_INTERNA"))
//                         .uri(urlReportes))
//                 .build();
//     }

// }
