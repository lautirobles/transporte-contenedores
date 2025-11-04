package com.backend.contenedores.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;

@Entity
@Table(name="camiones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Camion {
    
    private String dominio;

    @Column(name = "nombre_transportista")
    private String nombreTransportista;

    private int telefono;

    @Column(name = "cap_peso")
    private int capacidadPeso;

    @Column(name = "cap_volumen")
    private int capacidadVolumen;

    private boolean disponibilidad;

    private BigDecimal costos;
    
    @Column(name = "consumo_promedio")
    private BigDecimal consumoPromedio;
}
