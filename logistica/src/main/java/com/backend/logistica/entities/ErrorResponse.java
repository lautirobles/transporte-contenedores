package com.backend.logistica.entities;

import java.time.LocalDateTime;
import lombok.*;

@Data
public class ErrorResponse {
    private LocalDateTime timestamp;
    private int status;
    private String mensaje;

    public ErrorResponse(LocalDateTime timestamp, int status, String mensaje) {
        this.timestamp = timestamp;
        this.status = status;
        this.mensaje = mensaje;
    }
}
