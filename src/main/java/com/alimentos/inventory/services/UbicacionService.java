package com.alimentos.inventory.services;

import com.alimentos.inventory.entities.Ubicacion;
import com.alimentos.inventory.repositories.UbicacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UbicacionService {

    @Autowired
    private UbicacionRepository ubicacionRepository;

    public List<Ubicacion> getAllUbicaciones() {
        return ubicacionRepository.findAll();
    }

    public Ubicacion createUbicacion(Ubicacion ubicacion) {
        return ubicacionRepository.save(ubicacion);
    }

    public Ubicacion updateUbicacion(Long id, Ubicacion ubicacion) {
        if (ubicacionRepository.existsById(id)) {
            ubicacion.setId(id);
            return ubicacionRepository.save(ubicacion);
        }
        return null;
    }

    public boolean deleteUbicacion(Long id) {
        if (ubicacionRepository.existsById(id)) {
            ubicacionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
