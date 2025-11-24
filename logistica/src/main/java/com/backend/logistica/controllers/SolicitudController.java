package com.backend.logistica.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

import com.backend.logistica.entities.dto.SolicitudDto;
import com.backend.logistica.entities.dto.RutaDto;
import com.backend.logistica.entities.dto.UpdateSolicitudDto;
import com.backend.logistica.services.interfaces.SolicitudService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/logistica/solicitud")
public class SolicitudController {
    
    private final SolicitudService service;

    @GetMapping("/test")
    public String publicTest() {
        return "PUBLIC - SIN SEGURIDAD";
    }

    @GetMapping
    public ResponseEntity<List<SolicitudDto>> getSolicitudes(){
        var response = service.getAllSolicitudes();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/{idSolicitud}")
    public ResponseEntity<SolicitudDto> getSolicitudPorId(@PathVariable Long idSolicitud){
        var response = service.getSolicitud(idSolicitud);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/{idSolicitud}")
    public ResponseEntity<Void> updateFechaCostoFecha(
        @PathVariable Long idSolicitud,
        @RequestBody UpdateSolicitudDto dto){
        service.updateFechaCostoSolicitud(idSolicitud, dto);

        return ResponseEntity.ok(null);
    }

    @PatchMapping("/{idSolicitud}/ruta-asignada")
    public ResponseEntity<Void> updateRutaAsignada(
        @PathVariable Long idSolicitud,
        @RequestBody RutaDto dto){
        service.updateRutaAsignadaSolicitud(idSolicitud,dto);

        return ResponseEntity.ok(null);
    }

    @PostMapping
    public ResponseEntity<SolicitudDto> createSolicitud(
        @RequestBody SolicitudDto solicitudDto){
            var response = service.createSolicitud(solicitudDto);

            return ResponseEntity.ok(response);
    }
    

}
