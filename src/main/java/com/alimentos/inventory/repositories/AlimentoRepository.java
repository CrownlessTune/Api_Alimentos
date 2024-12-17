package com.alimentos.inventory.repositories;

import com.alimentos.inventory.entities.Alimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface AlimentoRepository extends JpaRepository<Alimento, Long> {

    List<Alimento> findByUbicacionId(Long ubicacionId);

    @Query("SELECT a FROM Alimento a WHERE a.fechaCaducidad <= :fecha")
    List<Alimento> findAlimentosProximosACaducar(LocalDate fecha);
}
