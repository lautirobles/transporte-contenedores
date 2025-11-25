package com.backend.logistica.services;

import com.backend.logistica.repositories.TramoRepositoryImpl;
import com.backend.logistica.services.interfaces.TramoService;
import com.backend.logistica.entities.Tramo;
import com.backend.logistica.clients.CamionesClient;
import com.backend.logistica.entities.Ruta;
import com.backend.logistica.entities.dto.CamionDto;
import com.backend.logistica.entities.dto.RutaDto;
import com.backend.logistica.entities.dto.TramoDto;
import com.backend.logistica.mapper.TramoMapper;
import com.backend.logistica.mapper.RutaMapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.*;

@RequiredArgsConstructor
@Service
public class TramoServiceImpl implements TramoService {
    
    private final TramoRepositoryImpl tramoRepository;

    @Autowired
    private CamionesClient camionesClient;

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
        
        if("EN_PROCESO".equals(estado) && "PENDIENTE".equals(tramo.getEstado())){
            tramo.setFechaHoraInicioReal(LocalDateTime.now());
            tramo.setEstado(estado);
        }else if("COMPLETADO".equals(estado) && "EN_PROCESO".equals(tramo.getEstado())){
            tramo.setFechaHoraFinReal(LocalDateTime.now());
            tramo.setEstado(estado);
        }else{
            throw new IllegalArgumentException("No puede asignarse ese estado al tramo, estado actual del tramo: " + tramo.getEstado());
        }

        tramoRepository.save(tramo);
    }

    @Override
    public void updateCamion(Long id, Long idCamion){
        Tramo tramo = tramoRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Tramo no encontrado con id: " + id));
        
        if(tramo.getCamion() != null){
            throw new IllegalStateException("No se puede asignar: El tramo " + id + " ya tiene un camión asignado: " + tramo.getCamion());
        }

        CamionDto camionDto = camionesClient.obtenerPorId(idCamion);

        if(camionDto == null){
            throw new NoSuchElementException("Cliente no encontrado en Gestion (devuelve null)");
        }
        
        tramo.setCamion(idCamion);
        tramoRepository.save(tramo);

    }
}
