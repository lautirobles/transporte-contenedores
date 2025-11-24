package com.backend.gestion.entities;


import java.math.BigDecimal;

import jakarta.persistence.*;

// import java.time.LocalDateTime;
// import java.util.ArrayList;
// import java.util.List;

// import com.fasterxml.jackson.annotation.JsonIgnore;


import lombok.*;


@Entity
@Table(name = "depositos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Deposito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private String direccion;
    private BigDecimal latitud;
    private BigDecimal longitud;
    // @OneToMany(mappedBy = "deposito", cascade = CascadeType.ALL, orphanRemoval = true)
    // @JsonIgnore
    // private List<Estadia> estadias = new ArrayList<>();
}