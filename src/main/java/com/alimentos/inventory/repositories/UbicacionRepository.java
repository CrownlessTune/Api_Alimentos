package com.alimentos.inventory.repositories;

import com.alimentos.inventory.entities.Ubicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UbicacionRepository extends JpaRepository<Ubicacion, Long> {

    // Metodo para encontrar una ubicación por su descripción
    Optional<Ubicacion> findByDescripcion(String descripcion);

    // Consulta personalizada usando @Query
    @Query("SELECT u FROM Ubicacion u WHERE u.descripcion = ?1")
    Optional<Ubicacion> findUbicacionByDescripcion(String descripcion);
}
