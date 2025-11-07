package com.backend.logistica.controllers;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.backend.logistica.services.interfaces.CambioEstadoService;

// import lombok.AllArgsConstructor;
// import lombok.Getter;

import java.util.List;
// import java.util.NoSuchElementException;

// import org.apache.catalina.connector.Response;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.PathVariable;
// import org.springframework.web.bind.annotation.DeleteMapping;

import com.backend.logistica.entities.CambioEstado;
// import com.backend.logistica.exception.GlobalExceptionHandler;

@RestController
@RequestMapping("api/v1/logistica/cambio-estado")
public class CambioEstadoController {
    private final CambioEstadoService service;

    public CambioEstadoController(CambioEstadoService service){
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<CambioEstado>> getAllCambioEstado(){
        return ResponseEntity.ok(service.getAllCambiosEstado());    
    }
    
    @PostMapping
    public ResponseEntity<CambioEstado> createCambioEstado(@RequestBody CambioEstado cambioEstado){    
        return ResponseEntity.ok(service.createCambioEstado(cambioEstado));
    }
}
