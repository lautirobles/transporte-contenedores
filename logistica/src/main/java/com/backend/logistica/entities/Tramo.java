package com.backend.logistica.entities;


import jakarta.persistence.*;

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

    @Column(name="costo_aproximado")
    private Double costoAproximado;
    @Column(name="costo_real")
    private Double costoReal;

    @Column(name="fecha_hora_inicio_estimada")
    private LocalDateTime fechaHoraInicioEstimada;
    @Column(name="fecha_hora_fin_estimada")
    private LocalDateTime fechaHoraFinEstimada;
    @Column(name="fecha_hora_inicio_real")
    private LocalDateTime fechaHoraInicioReal;
    @Column(name="fecha_hora_fin_real")
    private LocalDateTime fechaHoraFinReal;

    // Camión asignado al tramo
    // @ManyToOne
    @Column(name="camion_id")
    private Long camion;

    // Ruta a la que pertenece el tramo
    @ManyToOne
    @JoinColumn(name = "ruta_id")
    @ToString.Exclude            
    @EqualsAndHashCode.Exclude
    private Ruta ruta;
}