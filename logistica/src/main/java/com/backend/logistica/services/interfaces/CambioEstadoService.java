package com.backend.logistica.services.interfaces;

import com.backend.logistica.entities.CambioEstado;
import java.util.List;

public interface CambioEstadoService {
    
    List<CambioEstado> getAllCambiosEstado();
    
    CambioEstado createCambioEstado(CambioEstado cambioEstado);

    CambioEstado updateCambioEstado(Long id, CambioEstado cambioEstado);

    void deleteCambioEstado(Long id);

}
