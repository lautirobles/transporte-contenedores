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
    private Cliente cliente;

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