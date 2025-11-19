package com.backend.logistica.services;

import com.backend.logistica.repositories.ContenedorRepositoryImpl;
import com.backend.logistica.repositories.RutaRepositoryImpl;
import com.backend.logistica.repositories.SolicitudRepositoryImpl;
import com.backend.logistica.services.interfaces.SolicitudService;

import lombok.RequiredArgsConstructor;

import com.backend.logistica.entities.Ruta;
import com.backend.logistica.entities.Solicitud;
import com.backend.logistica.entities.dto.RutaDto;
import com.backend.logistica.entities.dto.SolicitudDto;
import com.backend.logistica.entities.dto.UpdateSolicitudDto;
import com.backend.logistica.mapper.RutaMapper;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.backend.logistica.mapper.SolicitudMapper;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SolicitudServiceImpl implements SolicitudService {
    
    private final SolicitudRepositoryImpl solicitudRepository;
    private final RutaRepositoryImpl rutaRepository;
    private final ContenedorRepositoryImpl contenedorRepository;

    @Override
    public List<SolicitudDto> getAllSolicitudes(){
        return solicitudRepository.findAll().stream()
                    .map(SolicitudMapper::entityToDto)
                    .collect(Collectors.toList());
    }

    @Override
    public SolicitudDto getSolicitud(Long numero){
        return SolicitudMapper.entityToDto(solicitudRepository.findById(numero).orElse(null));    
    }

    @Override
    public SolicitudDto createSolicitud(SolicitudDto dto){
        Solicitud solicitud = SolicitudMapper.dtoToEntity(dto);

        // FALTAN VALIDACIONESSSSSS

        var ruta = rutaRepository.findById(dto.getRutaAsignada()).orElse(null);

        var contenedor = contenedorRepository.findById(dto.getContenedor()).orElse(null);

        solicitud.setRutaAsignada(ruta);
        solicitud.setContenedor(contenedor);
        solicitudRepository.save(solicitud);
        return SolicitudMapper.entityToDto(solicitud);
    }

    @Override
    public SolicitudDto updateSolicitud(Long numero, Solicitud solicitud){
        if(solicitudRepository.existsById(numero)){
            solicitud.setNumero(numero);
            solicitudRepository.save(solicitud);
            return SolicitudMapper.entityToDto(solicitud);
        }
        return null;
    }

    @Override 
    public void updateFechaCostoSolicitud(Long numero, UpdateSolicitudDto dto){
        Solicitud solicitud = solicitudRepository.findById(numero).orElseThrow(()-> new NoSuchElementException("No se encontro solicitud con ese id"));
        if(solicitud != null){
            solicitud.setCostoFinal(dto.getCostoFinal());
            solicitud.setTiempoReal(dto.getTiempoReal());
            solicitudRepository.save(solicitud);
        }
    }

    @Override
    public void updateRutaAsignadaSolicitud(Long numero, RutaDto rutaAsignada){
        Solicitud solicitud = solicitudRepository.findById(numero).orElseThrow(()-> new NoSuchElementException("No se encontro solicitud con ese id"));
        if(solicitud != null){
            Ruta ruta = RutaMapper.dtoToEntity(rutaAsignada);
            solicitud.setRutaAsignada(ruta);
            solicitudRepository.save(solicitud);
        }
       
    }

    @Override
    public void deleteSolicitud(Long numero){
        if(!solicitudRepository.existsById(numero)){
            throw new NoSuchElementException("No existe una solicitud con id: " + numero);
        }else{
            solicitudRepository.deleteById(numero);
        }


    }
}
