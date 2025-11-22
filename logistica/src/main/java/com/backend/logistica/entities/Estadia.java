package com.backend.logistica.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;



import lombok.*;

@Entity
@Table(name = "estadias")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Estadia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK a deposito
    // @ManyToOne
    @Column(name = "deposito_id")
    private Long deposito;

    // FK a contenedor
    @ManyToOne
    @JoinColumn(name = "contenedor_id")
    private Contenedor contenedor;

    @Column(name="fecha_ingreso")
    private LocalDateTime fechaIngreso;
    @Column(name="fecha_salida")
    private LocalDateTime fechaSalida;
}