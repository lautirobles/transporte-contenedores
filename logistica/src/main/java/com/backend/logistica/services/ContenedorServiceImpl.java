package com.backend.logistica.services;

import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.backend.logistica.mapper.ContenedorMapper;
import com.backend.logistica.repositories.ContenedorRepositoryImpl;
import com.backend.logistica.services.interfaces.ContenedorService;
import com.backend.logistica.entities.dto.ContenedorDto;

import java.util.List;
import lombok.*;

@Service
@RequiredArgsConstructor
public class ContenedorServiceImpl implements ContenedorService {
    
    private final ContenedorRepositoryImpl contenedorRepository;

    public List<ContenedorDto> getAllContenedores(){
        return contenedorRepository.findAll()
            .stream()
            .map(ContenedorMapper::entityToDto)
            .collect(Collectors.toList());
    }

    public ContenedorDto getContenedorPorId(Long id){

        return ContenedorMapper.entityToDto(
            contenedorRepository.findById(id)
            .orElse(null));
    }

}
