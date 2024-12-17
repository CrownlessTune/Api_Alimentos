package com.alimentos.inventory.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "alimentos")
@Getter @Setter @NoArgsConstructor
public class Alimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String tipo; // Perecedero / No perecedero

    private String estado; // Abierto / Cerrado

    private LocalDate fechaCaducidad;

    // Relación OneToMany con Existencia
    @OneToMany(mappedBy = "alimento", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Existencia> existencias;

    // Relación ManyToOne con Ubicacion (el Alimento pertenece a una Ubicacion)
    @ManyToOne
    @JoinColumn(name = "ubicacion_id")  // El nombre de la columna en la base de datos
    private Ubicacion ubicacion;  // Relación con Ubicacion
}
