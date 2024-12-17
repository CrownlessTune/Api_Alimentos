package com.alimentos.inventory.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "ubicaciones")
@Getter @Setter @NoArgsConstructor
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descripcion; // Ej: Balda superior en la alacena

    private String tipoUbicacion; // Alacena, Nevera, Congelador

    private Integer capacidad;

    @OneToMany(mappedBy = "ubicacion", cascade = CascadeType.ALL)
    private List<Existencia> existencias;
}
