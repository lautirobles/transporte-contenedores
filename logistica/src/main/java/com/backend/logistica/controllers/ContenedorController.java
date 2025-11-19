package com.backend.logistica.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backend.logistica.services.interfaces.ContenedorService;
import com.backend.logistica.entities.dto.ContenedorDto;

import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/logistica/contenedor")
public class ContenedorController {

    private final ContenedorService service;

    @GetMapping
    public ResponseEntity<List<ContenedorDto>> getAllContenedores(){
        var response = service.getAllContenedores();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idContenedor}")
    public ResponseEntity<ContenedorDto> getContenedorPorId(@PathVariable Long idContenedor){
        var response = service.getContenedorPorId(idContenedor);

        return ResponseEntity.ok(response);
    }
    
}
