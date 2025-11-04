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
@Table(name = "rutas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ruta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer cantidadTramos;
    private Integer cantidadDepositos;

    /* 
    // Tramos que componen la ruta
    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Tramo> tramos = new ArrayList<>();

    // Si querés ver las solicitudes que usan esta ruta (bidireccional)
    @OneToMany(mappedBy = "rutaAsignada", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Solicitud> solicitudes = new ArrayList<>();
    */
}
