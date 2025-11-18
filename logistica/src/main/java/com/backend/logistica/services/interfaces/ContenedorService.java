package com.backend.logistica.services.interfaces;

import java.util.List;

import org.springframework.stereotype.Service;

import com.backend.logistica.entities.dto.ContenedorDto;

@Service
public interface ContenedorService {
 
    List<ContenedorDto> getAllContenedores();

    ContenedorDto getContenedorPorId(Long id);

}
