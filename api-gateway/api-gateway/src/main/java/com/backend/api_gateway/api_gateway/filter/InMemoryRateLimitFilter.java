package com.backend.api_gateway.api_gateway.filter;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Component
@Slf4j
public class InMemoryRateLimitFilter implements GlobalFilter, Ordered {

    private static final int REQUESTS_PER_MINUTE = 20;
    private static final Duration WINDOW = Duration.ofMinutes(1);
    
    private final Map<String, RequestCount> requestCounts = new ConcurrentHashMap<>();

    private static class RequestCount {
        private int count;
        private long lastRequestTime;

        public RequestCount() {
            this.count = 1;
            this.lastRequestTime = System.currentTimeMillis();
        }

        public boolean incrementIfAllowed() {
            long now = System.currentTimeMillis();
            if (now - lastRequestTime > WINDOW.toMillis()) {
                count = 1;
                lastRequestTime = now;
                return true;
            }
            
            if (count < REQUESTS_PER_MINUTE) {
                count++;
                return true;
            }
            
            return false;
        }
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String ip = exchange.getRequest().getRemoteAddress().getAddress().getHostAddress();
        
        RequestCount requestCount = requestCounts.computeIfAbsent(ip, k -> new RequestCount());
        
        if (!requestCount.incrementIfAllowed()) {
            exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
            log.warn("Rate limit exceeded for IP: {}", ip);
            return exchange.getResponse().setComplete();
        }
        
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }

  private static class Counter {
    AtomicInteger count = new AtomicInteger(0);
    volatile long windowStart = Instant.now().toEpochMilli();
  }

  private final Map<String, Counter> buckets = new ConcurrentHashMap<>();

  @Override
  public Mono<Void> filter(ServerWebExchange exchange, org.springframework.cloud.gateway.filter.GatewayFilterChain chain) {
    ServerHttpRequest req = exchange.getRequest();
    String path = req.getURI().getPath();
    if (!path.startsWith("/api/horarios/")) {
      return chain.filter(exchange); // limitar solo el servicio costoso
    }

    String ip = req.getHeaders().getFirst("X-Forwarded-For");
    if (ip == null || ip.isBlank()) {
      ip = req.getRemoteAddress() != null ? req.getRemoteAddress().getAddress().getHostAddress() : "unknown";
    }

    Counter c = buckets.computeIfAbsent(ip, k -> new Counter());
    long now = Instant.now().toEpochMilli();
    synchronized (c) {
      if (now - c.windowStart > WINDOW_MS) {
        c.windowStart = now;
        c.count.set(0);
      }
      int current = c.count.incrementAndGet();
      if (current > LIMIT) {
        exchange.getResponse().setStatusCode(HttpStatus.TOO_MANY_REQUESTS);
        return exchange.getResponse().setComplete();
      }
    }
    return chain.filter(exchange);
  }

  @Override
  public int getOrder() { return 10; }
}
