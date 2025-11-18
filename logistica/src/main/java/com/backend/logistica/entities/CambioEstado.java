package com.backend.logistica.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


import lombok.*;

@Entity
@Table(name = "cambios_estado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CambioEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK a contenedor
    @ManyToOne
    @JoinColumn(name = "contenedor_id")
    private Contenedor contenedor;

    @Column(name="estado_anterior")
    private String estadoAnterior;
    @Column(name="estado_nuevo")
    private String estadoNuevo;
    @Column(name="fecha_cambio")
    private LocalDateTime fechaCambio;
}