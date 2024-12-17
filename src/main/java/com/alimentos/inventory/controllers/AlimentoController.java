package com.alimentos.inventory.controllers;

import com.alimentos.inventory.entities.Alimento;
import com.alimentos.inventory.services.AlimentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alimentos")
public class AlimentoController {

    @Autowired
    private AlimentoService service;

    @GetMapping
    public List<Alimento> getAllAlimentos() {
        return service.getAllAlimentos();
    }

    @PostMapping
    public Alimento saveAlimento(@RequestBody Alimento alimento) {
        return service.saveAlimento(alimento);
    }

    @DeleteMapping("/{id}")
    public void deleteAlimento(@PathVariable Long id) {
        service.deleteAlimento(id);
    }

    @PutMapping("/rotar/{ubicacionIdPrioridad}")
    public void rotarAlimentos(@PathVariable Long ubicacionIdPrioridad) {
        service.rotarAlimentosProximosACaducar(ubicacionIdPrioridad);
    }
}
