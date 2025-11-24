package com.backend.logistica.controllers;

import com.backend.logistica.services.interfaces.RutaService;
import com.backend.logistica.entities.dto.RutaDto;
import com.backend.logistica.mapper.RutaMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;





@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/logistica/ruta")
public class RutaController {

    private final RutaService service;
    
    @GetMapping
    public ResponseEntity<List<RutaDto>> getRutas(){
        List<RutaDto> response = service.getAllRutas()
            .stream()
            .map(RutaMapper::entityToDto)
            .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{idRuta}")
    public ResponseEntity<RutaDto> getRutaId(@PathVariable Long idRuta){
        RutaDto response = RutaMapper.entityToDto(service.getRuta(idRuta));
        return ResponseEntity.ok(response);
    }

    @GetMapping("/rutas-tentativas")
    public ResponseEntity<List<RutaDto>> buscarRutas(
            @RequestParam String origen, 
            @RequestParam String destino) {
        
        List<RutaDto> response = service.getRutasAlternativas(origen, destino);
        
        if(response.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(response);
    }

}
