package com.backend.contenedores.controllers;

import java.util.NoSuchElementException;
import java.util.Set;

// import com.backend.contenedores.services.;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import com.backend.contenedores.entities.Ruta;

import io.swagger.v3.oas.annotations.parameters.RequestBody;


@RequiredArgsConstructor
@Controller
@RequestMapping("api/ruta")
public class RutaController {
 
    // private final RutasService service;
    
    @GetMapping
    public ResponseEntity<Set<RutaDto>> getRutas(){
        // var response = service.getAll();
    }

    @PostMapping
    public ResponseEntity<Object> createRuta(@RequestBody RutaSaveDto entity){
        try{
            var response = service.saveRuta(entity);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }catch (NoSuchElementException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
