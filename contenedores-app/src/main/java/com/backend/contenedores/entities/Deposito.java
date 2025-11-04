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
@Table(name = "depositos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Deposito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private BigDecimal coordenadas; // si necesitás lat/long separar en dos campos (lat, lon)

    @OneToMany(mappedBy = "deposito", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Estadia> estadias = new ArrayList<>();
}