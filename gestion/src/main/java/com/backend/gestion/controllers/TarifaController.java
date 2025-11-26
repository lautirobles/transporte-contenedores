package com.backend.gestion.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.backend.gestion.services.interfaces.TarifaService;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import com.backend.gestion.entities.Tarifa;
import com.backend.gestion.entities.dto.TarifasVigentesDto;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;


@RestController
@RequestMapping("/api/v1/gestion/tarifa")
public class TarifaController {
    
    private final TarifaService service;

    public TarifaController(TarifaService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Tarifa>> getAllTarifas() {
        return ResponseEntity.ok(service.getAllTarifas());
    }

    @PostMapping
    public ResponseEntity<Tarifa> createTarifa(@RequestBody Tarifa tarifa) {
        return ResponseEntity.ok(service.createTarifa(tarifa));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tarifa> updateTarifa(@PathVariable Long id, @RequestBody Tarifa tarifa) {
        return ResponseEntity.ok(service.updateTarifa(id, tarifa));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTarifa(@PathVariable Long id) {
        service.deleteTarifa(id);
        return ResponseEntity.noContent().build();
    }

        // Endpoint consolidado para Logística
    @GetMapping
    public ResponseEntity<TarifasVigentesDto> getTarifasVigentes() {
        return ResponseEntity.ok(service.getTarifasVigentes());
    }
}
