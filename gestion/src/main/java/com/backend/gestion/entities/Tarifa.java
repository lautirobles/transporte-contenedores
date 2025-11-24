package com.backend.gestion.entities;


import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;


import lombok.*;

@Entity
@Table(name = "tarifas")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Tarifa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo;
    private BigDecimal valor;

    @Column(name = "peso_min")
    private BigDecimal pesoMin;

    @Column(name = "peso_max")
    private BigDecimal pesoMax;
}