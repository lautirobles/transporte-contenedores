package com.backend.logistica.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.backend.logistica.services.interfaces.TramoService;
import com.backend.logistica.entities.dto.RutaDto;
import com.backend.logistica.entities.dto.TramoDto;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/tramo")
public class TramoController {
    
    private final TramoService service;

    @GetMapping
    public ResponseEntity<List<TramoDto>> getTramosPorRuta(@RequestBody RutaDto rutaDto){
        var response = service.getTramosPorRuta(rutaDto);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{idTramo}/estado")
    public ResponseEntity<Void> updateEstadoTramo(@PathVariable Long idTramo, @RequestBody String estado){
        service.updateEstadoTramo(idTramo, estado);
        return ResponseEntity.ok(null);
    }

    @PatchMapping("/{idTramo}/camion")
    public ResponseEntity<Void> updateCamionTramo(@PathVariable Long idTramo, @RequestBody Long idCamion){
        service.updateCamion(idTramo, idCamion);
        return ResponseEntity.ok(null);
    }
}
