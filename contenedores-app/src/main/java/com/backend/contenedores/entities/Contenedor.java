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
@Table(name = "contenedores")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
class Contenedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal peso;
    private BigDecimal volumen;
    private String estado;

    // cliente asociado (cl_asociado)
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente clienteAsociado;

    // Estancias en depósitos
    @OneToMany(mappedBy = "contenedor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Estadia> estadias = new ArrayList<>();

    // Historial de cambios de estado
    @OneToMany(mappedBy = "contenedor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CambioEstado> cambiosEstado = new ArrayList<>();

    // Si hay solicitudes que referencian este contenedor (opcional)
    @OneToMany(mappedBy = "contenedor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Solicitud> solicitudes = new ArrayList<>();
}