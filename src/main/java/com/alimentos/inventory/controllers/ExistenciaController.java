package com.alimentos.inventory.controllers;

import com.alimentos.inventory.entities.Existencia;
import com.alimentos.inventory.services.ExistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/existencias")
public class ExistenciaController {

    @Autowired
    private ExistenciaService service;

    @GetMapping
    public List<Existencia> getAllExistencias() {
        return service.getAllExistencias();
    }

    @PutMapping("/rotar")
    public void rotarExistencias() {
        service.rotarExistenciasProximasACaducar();
    }
}
