package com.backend.logistica.services;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.backend.logistica.entities.Ruta;
import com.backend.logistica.entities.dto.RutaDto;
import com.backend.logistica.mapper.RutaMapper;
import com.backend.logistica.repositories.RutaRepositoryImpl;
import com.backend.logistica.services.interfaces.RutaService;

import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RutaServiceImpl implements RutaService {
    
    private final RutaRepositoryImpl rutaRepositoryImpl;

    @Override
    public List<Ruta> getAllRutas(){
        return rutaRepositoryImpl.findAll();
    }

    @Override
    public Ruta getRuta(Long id){
        return rutaRepositoryImpl.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RutaDto> getRutasAlternativas(String origen, String destino) {
        List<Ruta> todasLasRutas = getAllRutas();
        // Filtramos las rutas que cumplan con el origen y destino

        List<RutaDto> resultado = todasLasRutas.stream()
            .filter(ruta -> esRutaValida(ruta, origen, destino))
            .map(RutaMapper::entityToDto)
            .collect(Collectors.toList());
            
        return resultado;
    }

    private boolean esRutaValida(Ruta ruta, String origenBuscado, String destinoBuscado) {

        if (ruta.getTramos() == null || ruta.getTramos().isEmpty()) {
            return false;
        }
        
        // Asumimos que la lista de tramos está ordenada. 
        
        // El origen de la ruta es el origen del primer tramo
        String origenRuta = ruta.getTramos().get(0).getOrigen();
        
        // El destino de la ruta es el destino del último tramo
        String destinoRuta = ruta.getTramos().get(ruta.getTramos().size() - 1).getDestino();
        
        return origenRuta.equalsIgnoreCase(origenBuscado) && destinoRuta.equalsIgnoreCase(destinoBuscado);
    }

}
