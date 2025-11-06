package com.backend.gestion.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;


import lombok.*;

@Entity
@Table(name="camiones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Camion {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
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
