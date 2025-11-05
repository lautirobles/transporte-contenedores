package com.backend.logistica.entities;


import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;


import lombok.*;

@Entity
@Table(name = "solicitudes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Solicitud {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long numero; // en el DER aparece 'numero' (PK)

    // FK a contenedor
    @ManyToOne
    @JoinColumn(name = "contenedor_id")
    private Contenedor contenedor;

    // FK a cliente
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Long cliente;

    private String estado;

    // Ruta asignada (opcional) -- ManyToOne a Ruta
    @ManyToOne
    @JoinColumn(name = "ruta_id")
    private Ruta rutaAsignada;

    private BigDecimal costoEstimado;
    private Integer tiempoEstimado; // en minutos o la unidad que uses
    private BigDecimal costoFinal;
    private Integer tiempoReal;
}