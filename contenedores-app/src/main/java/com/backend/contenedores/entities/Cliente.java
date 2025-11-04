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
@Table(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String razonSocial; // razon_social
    private String cuil;
    private String numero; // puede ser telefono o numero de cliente

    // Relaciones
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Solicitud> solicitudes = new ArrayList<>();

    @OneToMany(mappedBy = "clienteAsociado", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Contenedor> contenedores = new ArrayList<>();
}