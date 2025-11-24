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
import com.backend.logistica.entities.dto.SeguimientoSolicitudDto;
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

    // endpoint para actualizar el costo final y la fecha
    @PatchMapping("/{idSolicitud}")
    public ResponseEntity<Void> updateFechaCostoFecha(
        @PathVariable Long idSolicitud,
        @RequestBody UpdateSolicitudDto dto){
        service.updateFechaCostoSolicitud(idSolicitud, dto);

        return ResponseEntity.ok(null);
    }

    // asignar ruta a la solicitud
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
    
    /**
     * Endpoint para crear una solicitud completa.
     * Valida si el cliente existe; si no, lo crea.
     * Siempre crea un nuevo contenedor asociado a la solicitud.
     */
    @PostMapping("/completa")
    public ResponseEntity<SolicitudDto> createSolicitudCompleta(
        @RequestBody SolicitudDto solicitudDto) {
            var response = service.createSolicitudConClienteYContenedor(solicitudDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    // Endpoint para seguimiento (Requerimiento: Consultar el estado del transporte)
    @GetMapping("/{numero}/seguimiento")
    public ResponseEntity<SeguimientoSolicitudDto> getSeguimiento(@PathVariable Long numero) {
        SeguimientoSolicitudDto response = service.obtenerSeguimiento(numero);
        return ResponseEntity.ok(response);
    }
    

}
