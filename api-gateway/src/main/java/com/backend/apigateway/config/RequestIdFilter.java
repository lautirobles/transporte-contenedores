// package com.backend.api_gateway.api_gateway.config;
// package com.backend.api_gateway.filter;

// 
// import java.util.UUID;

// import org.springframework.cloud.gateway.filter.GlobalFilter;
// import org.springframework.core.Ordered;
// import org.springframework.stereotype.Component;
// import org.springframework.web.server.ServerWebExchange;

// import reactor.core.publisher.Mono;

// @Component
// public class RequestIdFilter implements GlobalFilter, Ordered {

//   public static final String HDR = "X-Request-Id";

//   @Override
//   public Mono<Void> filter(ServerWebExchange exchange,
//                           org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {

//     var request = exchange.getRequest();
//     String reqId = request.getHeaders().getFirst(HDR);
//     if (reqId == null || reqId.isBlank()) {
//       reqId = UUID.randomUUID().toString();
//       // Mutar request con el header
//       exchange = exchange.mutate()
//           .request(request.mutate().header(HDR, reqId).build())
//           .build();
//     }

//     // IMPORTANTE: setear el header en la respuesta ANTES de continuar el chain
//     exchange.getResponse().getHeaders().set(HDR, reqId);

//     return chain.filter(exchange);
//   }

//   @Override
//   public int getOrder() {
//     return -100; // muy temprano
//   }
// }