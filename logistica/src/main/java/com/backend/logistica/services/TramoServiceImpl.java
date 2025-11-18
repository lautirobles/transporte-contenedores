package com.backend.logistica.services;

import com.backend.logistica.repositories.TramoRepositoryImpl;
import com.backend.logistica.services.interfaces.TramoService;
import com.backend.logistica.entities.Tramo;
import com.backend.logistica.entities.Ruta;
import com.backend.logistica.entities.dto.RutaDto;
import com.backend.logistica.entities.dto.TramoDto;
import com.backend.logistica.mapper.TramoMapper;
import com.backend.logistica.mapper.RutaMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import lombok.*;

@RequiredArgsConstructor
@Service
public class TramoServiceImpl implements TramoService {
    
    private final TramoRepositoryImpl tramoRepository;

    @Override
    public List<TramoDto> getTramosPorRuta(RutaDto rutaDto){
        // Convertir el RutaDto a entidad usando el mapper
        Ruta ruta = RutaMapper.dtoToEntity(rutaDto);
        
        // Buscar tramos por ruta
        List<Tramo> tramos = tramoRepository.findByRuta(ruta);
        
        return tramos.stream()
            .map(TramoMapper::entityToDto)
            .collect(Collectors.toList());
    }

    @Override
    public void updateEstadoTramo(Long idTramo, String estado){
        Tramo tramo = tramoRepository.findById(idTramo)
            .orElseThrow(() -> new IllegalArgumentException("Tramo no encontrado con id: " + idTramo));
        
        
        tramo.setEstado(estado);
        if("Iniciado".equals(estado)){
            tramo.setFechaHoraInicioReal(LocalDateTime.now());
        }else if("Finalizado".equals(estado)){
            tramo.setFechaHoraFinReal(LocalDateTime.now());
        }

        tramoRepository.save(tramo);
    }

    @Override
    public void updateCamion(Long id, Long idCamion){
        Tramo tramo = tramoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Tramo no encontrado con id: " + id));
        
        tramo.setCamion(idCamion);

        tramoRepository.save(tramo);
    }
}
