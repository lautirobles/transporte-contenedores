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
@Table(name = "cambios_estado")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class CambioEstado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // FK a contenedor
    @ManyToOne
    @JoinColumn(name = "contenedor_id")
    private Contenedor contenedor;

    private String estadoAnterior;
    private String estadoNuevo;
    private LocalDateTime fechaCambio;
}