package com.backend.logistica.entities.dto;

import lombok.Data;

@Data
public class ClienteDTO {
    private Long id;
    private String razonSocial;
    private String cuil;
    private Long numero;
    
}
