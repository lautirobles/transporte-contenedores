package com.backend.gestion.services.interfaces;
import com.backend.gestion.entities.Camion;
import java.util.List;

public interface CamionService {

    List<Camion> getAllCamiones();

    Camion getCamionPorId(Long id);

    Camion createCamion(Camion camion);

    Camion updateCamion(Long id, Camion camion);

    void deleteCamion(Long id);
}
