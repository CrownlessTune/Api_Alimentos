package com.alimentos.inventory.repositories;

import com.alimentos.inventory.entities.Existencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;


public interface ExistenciaRepository extends JpaRepository<Existencia, Long> {

    @Query("SELECT e FROM Existencia e WHERE e.alimento.fechaCaducidad <= :fecha")
    List<Existencia> findExistenciasProximasACaducar(java.time.LocalDate fecha);
}