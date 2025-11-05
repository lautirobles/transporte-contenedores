package com.backend.gestion.controllers;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.backend.gestion.entities.Cliente;
import com.backend.gestion.services.interfaces.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    private final ClienteService service;

    public ClienteController(ClienteService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> getAllClientes() {
        return ResponseEntity.ok(service.getAllClientes());
    }

    
}
