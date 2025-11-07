package com.backend.logistica.services;
import com.backend.logistica.repositories.CambioEstadoRepositoryImpl;
import com.backend.logistica.services.interfaces.CambioEstadoService;
import com.backend.logistica.entities.CambioEstado;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.dao.DataAccessException;

public class CambioEstadoServiceImpl implements CambioEstadoService {
    
    private final CambioEstadoRepositoryImpl cambioEstadoRepository;

    public CambioEstadoServiceImpl(CambioEstadoRepositoryImpl cambioEstadoRepository){
        this.cambioEstadoRepository = cambioEstadoRepository;
    }

    @Override
    public List<CambioEstado> getAllCambiosEstado(){
        return cambioEstadoRepository.findAll();
    }

    @Override
    public CambioEstado createCambioEstado(CambioEstado cambioEstado){
        if (cambioEstado.getEstadoAnterior() == null && cambioEstado.getEstadoNuevo() == null) {
            throw new IllegalArgumentException("El estado no puede ser nulo.");
        }
        try{
            return cambioEstadoRepository.save(cambioEstado);
        }catch(DataAccessException e){
            throw new RuntimeException("Error al acceder a la base de datos...");
        }
    }

    @Override
    public CambioEstado updateCambioEstado(Long id, CambioEstado cambioEstado){
        try{
            if(cambioEstadoRepository.existsById(id)){
                cambioEstado.setId(id);
                return cambioEstadoRepository.save(cambioEstado);
            }
            return null;
        }catch(NoSuchElementException e){
            throw new NoSuchElementException("No se encontro el cambio de estado con ID: " + id);
        }
    }

    @Override
    public void deleteCambioEstado(Long id){
        if(!cambioEstadoRepository.existsById(id)){
            throw new NoSuchElementException("No existe un cambio estado con ID: " + id);
        }
        cambioEstadoRepository.deleteById(id);
    }
}
