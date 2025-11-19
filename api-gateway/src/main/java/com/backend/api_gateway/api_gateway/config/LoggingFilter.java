// package com.backend.api_gateway.api_gateway.config;
// package com.backend.api_gateway.filter;

// import org.springframework.cloud.gateway.filter.GlobalFilter;
// import org.springframework.core.Ordered;
// import org.springframework.stereotype.Component;
// import org.springframework.web.server.ServerWebExchange;

// import lombok.extern.slf4j.Slf4j;
// import reactor.core.publisher.Mono;

// @Slf4j
// @Component
// public class LoggingFilter implements GlobalFilter, Ordered {

//   @Override
//   public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
//     var req = exchange.getRequest();
//     String id = req.getHeaders().getFirst(RequestIdFilter.HDR);
//     log.info("[{}] --> {} {}", id, req.getMethod(), req.getURI());
//     return chain.filter(exchange)
//         .doOnSuccess(v -> log.info("[{}] <-- {} {}", id, exchange.getResponse().getStatusCode(), req.getURI()));
//   }

//   @Override
//   public int getOrder() { return 0; }
// }
