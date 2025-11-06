package com.backend.gestion.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.backend.gestion.services.interfaces.DepositoService;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;

import com.backend.gestion.entities.Deposito;


@RestController
@RequestMapping("/api/v1/gestion/deposito")
public class DepositoController {
    
    private final DepositoService service;

    public DepositoController(DepositoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Deposito>> getAllDepositos() {
        return ResponseEntity.ok(service.getAllDepositos());
    }

    @PostMapping
    public ResponseEntity<Deposito> createDeposito(@RequestBody Deposito deposito) {
        return ResponseEntity.ok(service.createDeposito(deposito));
    }

    @PutMapping
    public ResponseEntity<Deposito> updateDeposito(@PathVariable Long id, @RequestBody Deposito deposito) {
        return ResponseEntity.ok(service.updateDeposito(id, deposito));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteDeposito(@PathVariable Long id) {
        service.deleteDeposito(id);
        return ResponseEntity.noContent().build();
    }

}   
