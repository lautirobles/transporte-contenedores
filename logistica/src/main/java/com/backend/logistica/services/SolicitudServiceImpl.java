package com.backend.logistica.services;

import com.backend.logistica.repositories.SolicitudRepositoryImpl;
import com.backend.logistica.services.interfaces.SolicitudService;
import com.backend.logistica.entities.Ruta;
import com.backend.logistica.entities.Solicitud;
import com.backend.logistica.entities.dto.RutaDto;
import com.backend.logistica.entities.dto.UpdateSolicitudDto;
import com.backend.logistica.mapper.RutaMapper;

import java.util.List;
import java.util.NoSuchElementException;


import org.springframework.stereotype.Service;


@Service
public class SolicitudServiceImpl implements SolicitudService {
    
    private final SolicitudRepositoryImpl solicitudRepository;

    public SolicitudServiceImpl(SolicitudRepositoryImpl solicitudRepository){
        this.solicitudRepository = solicitudRepository;
    }

    @Override
    public List<Solicitud> getAllSolicitudes(){
        return solicitudRepository.findAll();
    }

    @Override
    public Solicitud getSolicitud(Long numero){
        return solicitudRepository.findById(numero).orElse(null);    
    }

    @Override
    public Solicitud createSolicitud(Solicitud solicitud){
        return solicitudRepository.save(solicitud);
    }

    @Override
    public Solicitud updateSolicitud(Long numero, Solicitud solicitud){
        if(solicitudRepository.existsById(numero)){
            solicitud.setNumero(numero);
            return solicitudRepository.save(solicitud);
        }
        return null;
    }

    @Override 
    public Solicitud updateFechaCostoSolicitud(Long numero, UpdateSolicitudDto dto){
        Solicitud solicitud = solicitudRepository.findById(numero).orElseThrow(()-> new NoSuchElementException("No se encontro solicitud con ese id"));
        if(solicitud != null){
            solicitud.setCostoFinal(dto.getCostoFinal());
            solicitud.setTiempoReal(dto.getTiempoReal());
            return solicitudRepository.save(solicitud);
        }
        return null;
    }

    @Override
    public Solicitud updateRutaAsignadaSolicitud(Long numero, RutaDto rutaAsignada){
        Solicitud solicitud = solicitudRepository.findById(numero).orElseThrow(()-> new NoSuchElementException("No se encontro solicitud con ese id"));
        if(solicitud != null){
            Ruta ruta = RutaMapper.dtoToEntity(rutaAsignada);
            solicitud.setRutaAsignada(ruta);
            return solicitudRepository.save(solicitud);
        }
        return null;
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
