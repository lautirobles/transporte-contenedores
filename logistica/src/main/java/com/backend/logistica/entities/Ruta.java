package com.backend.logistica.entities;


import java.util.ArrayList;
import java.util.List;

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

    @Column(name="cantidad_tramos")
    private Integer cantidadTramos;
    @Column(name="cantidad_depositos")
    private Integer cantidadDepositos;

    
    // Tramos que componen la ruta
    @OneToMany(mappedBy = "ruta", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @OrderBy("idTramo ASC")
    @Builder.Default
    @ToString.Exclude          
    @EqualsAndHashCode.Exclude
    private List<Tramo> tramos = new ArrayList<>();

    // Si querés ver las solicitudes que usan esta ruta (bidireccional)
    // @OneToMany(mappedBy = "rutaAsignada", cascade = CascadeType.ALL)
    // @JsonIgnore
    // private List<Solicitud> solicitudes = new ArrayList<>();
}
