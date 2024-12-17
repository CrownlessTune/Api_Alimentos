package com.alimentos.inventory.services;

import com.alimentos.inventory.entities.Existencia;
import com.alimentos.inventory.repositories.ExistenciaRepository;
import com.alimentos.inventory.entities.Ubicacion;
import com.alimentos.inventory.repositories.UbicacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExistenciaService {

    private final ExistenciaRepository existenciaRepository;
    private final UbicacionRepository ubicacionRepository;

    public ExistenciaService(ExistenciaRepository existenciaRepository, UbicacionRepository ubicacionRepository) {
        this.existenciaRepository = existenciaRepository;
        this.ubicacionRepository = ubicacionRepository;
    }

    public List<Existencia> getAllExistencias() {
        return existenciaRepository.findAll();
    }

    @Transactional
    public void rotarExistenciasProximasACaducar() {
        LocalDate fechaLimite = LocalDate.now().plusDays(3);

        // Buscar existencias que están próximas a caducar
        List<Existencia> proximasACaducar = existenciaRepository.findExistenciasProximasACaducar(fechaLimite);

        if (proximasACaducar.isEmpty()) {
            throw new RuntimeException("No hay existencias próximas a caducar");
        }

        // Lógica de rotación: cambiar la ubicación de las existencias
        Ubicacion ubicacionCongelador = ubicacionRepository.findByDescripcion("Congelador")
                .orElseThrow(() -> new RuntimeException("Ubicación 'Congelador' no encontrada"));

        proximasACaducar.forEach(existencia -> {
            // Cambiar la ubicación de las existencias a 'Congelador'
            existencia.setUbicacion(ubicacionCongelador);
        });

        existenciaRepository.saveAll(proximasACaducar);
    }
}
