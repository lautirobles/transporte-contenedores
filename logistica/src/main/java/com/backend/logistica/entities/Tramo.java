package com.backend.logistica.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


import lombok.*;

@Entity
@Table(name = "tramos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Tramo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTramo;

    private String origen;
    private String destino;
    private String tipo;
    private String estado;

    private double costoAproximado;
    private double costoReal;

    private LocalDateTime fechaHoraInicioEstimada;
    private LocalDateTime fechaHoraFinEstimada;
    private LocalDateTime fechaHoraInicioReal;
    private LocalDateTime fechaHoraFinReal;

    // Camión asignado al tramo
    @ManyToOne
    @JoinColumn(name = "camion_id")
    private String camion;

    // Ruta a la que pertenece el tramo
    @ManyToOne
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;
}