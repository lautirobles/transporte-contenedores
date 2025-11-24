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
    // @ManyToOne
    @Column(name = "cliente_id")
    private Long cliente;

    private String estado;

    // Ruta asignada (opcional) -- ManyToOne a Ruta
    @ManyToOne
    @JoinColumn(name = "ruta_id")
    private Ruta rutaAsignada;

    @Column(name="costo_estimado")
    private BigDecimal costoEstimado;
    @Column(name="tiempo_estimado")
    private Integer tiempoEstimado; // en minutos o la unidad que uses
    @Column(name="costo_final")
    private BigDecimal costoFinal;
    @Column(name="tiempo_real")
    private Integer tiempoReal;
}