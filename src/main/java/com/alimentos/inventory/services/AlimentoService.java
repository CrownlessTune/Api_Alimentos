package com.alimentos.inventory.services;

import com.alimentos.inventory.entities.Alimento;
import com.alimentos.inventory.entities.Ubicacion;
import com.alimentos.inventory.entities.Existencia;
import com.alimentos.inventory.repositories.ExistenciaRepository;
import com.alimentos.inventory.repositories.AlimentoRepository;
import com.alimentos.inventory.repositories.UbicacionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AlimentoService {

    private final AlimentoRepository alimentoRepository;
    private final UbicacionRepository ubicacionRepository;
    private final ExistenciaRepository existenciaRepository;

    // Obtener todos los alimentos
    public List<Alimento> getAllAlimentos() {
        return alimentoRepository.findAll();
    }

    // Guardar o actualizar un alimento
    public Alimento saveAlimento(Alimento alimento) {
        // Validar que la fecha de caducidad no esté en el pasado
        if (alimento.getFechaCaducidad().isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("La fecha de caducidad no puede estar en el pasado");
        }
        return alimentoRepository.save(alimento);
    }

    // Eliminar un alimento por ID
    public void deleteAlimento(Long id) {
        if (!alimentoRepository.existsById(id)) {
            throw new RuntimeException("El alimento con ID " + id + " no existe");
        }
        alimentoRepository.deleteById(id);
    }

    // Método que rota los alimentos próximos a caducar hacia una ubicación de prioridad
    @Transactional
    public void rotarAlimentosProximosACaducar(Long ubicacionIdPrioridad) {
        LocalDate fechaLimite = LocalDate.now().plusDays(3);

        Ubicacion ubicacionPrioridad = ubicacionRepository.findById(ubicacionIdPrioridad)
                .orElseThrow(() -> new RuntimeException("Ubicación no encontrada"));

        List<Existencia> existenciasProximasACaducar = existenciaRepository.findExistenciasProximasACaducar(fechaLimite);

        if (existenciasProximasACaducar.isEmpty()) {
            throw new RuntimeException("No hay alimentos próximos a caducar");
        }

        // Rotar las existencias de alimentos
        existenciasProximasACaducar.forEach(existencia -> existencia.setUbicacion(ubicacionPrioridad));
        existenciaRepository.saveAll(existenciasProximasACaducar);
    }
}
