package com.backend.logistica.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import com.backend.logistica.entities.Solicitud;
import com.backend.logistica.entities.dto.RutaDto;
import com.backend.logistica.entities.dto.UpdateSolicitudDto;
import com.backend.logistica.services.interfaces.SolicitudService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("api/v1/logistica/solicitud")
public class SolicitudController {
    
    private final SolicitudService service;

    @GetMapping
    public ResponseEntity<List<Solicitud>> getSolicitudes(){
        var response = service.getAllSolicitudes();
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @GetMapping("/{idSolicitud}")
    public ResponseEntity<Solicitud> getSolicitudPorId(@PathVariable Long idSolicitud){
        var response = service.getSolicitud(idSolicitud);
        return ResponseEntity.status(HttpStatus.FOUND).body(response);
    }

    @PatchMapping("/{idSolicitud}")
    public ResponseEntity<Solicitud> updateFechaCostoFecha(
        @PathVariable Long idSolicitud,
        @RequestBody UpdateSolicitudDto dto){
        var response = service.updateFechaCostoSolicitud(idSolicitud, dto);

        return ResponseEntity.ok(response);
    }

    @PatchMapping("/{idSolicitud}/ruta-asignada")
    public ResponseEntity<Solicitud> updateRutaAsignada(
        @PathVariable Long idSolicitud,
        @RequestBody RutaDto dto){
        var response = service.updateRutaAsignadaSolicitud(idSolicitud,dto);

        return ResponseEntity.ok(response);
    }
    

}
