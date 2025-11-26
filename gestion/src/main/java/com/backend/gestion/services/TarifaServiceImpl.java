package com.backend.gestion.services;

import org.springframework.stereotype.Service;
import com.backend.gestion.services.interfaces.TarifaService;
import com.backend.gestion.entities.Tarifa;
import com.backend.gestion.entities.dto.TarifasVigentesDto;
import com.backend.gestion.repositories.TarifaRepository;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TarifaServiceImpl implements TarifaService{
    
    private final TarifaRepository tarifaRepository;

    public TarifaServiceImpl(TarifaRepository tarifaRepository) {
        this.tarifaRepository = tarifaRepository;
    }

    public List<Tarifa> getAllTarifas() {
        return tarifaRepository.findAll();
    }

    public Tarifa createTarifa(Tarifa tarifa) {
        return tarifaRepository.save(tarifa);
    }

    public Tarifa updateTarifa(Long id, Tarifa tarifa) {
        if (tarifaRepository.existsById(id)) {
            tarifa.setId(id);
            return tarifaRepository.save(tarifa);
        }
        return null;
    }

    public void deleteTarifa(Long id) {
        tarifaRepository.deleteById(id);
    }

    @Override
    public TarifasVigentesDto getTarifasVigentes() {
        List<Tarifa> todas = tarifaRepository.findAll();
        
        // Buscamos cada tarifa por su "tipo" (asegurate que coincida con tu data.sql)
        BigDecimal combustible = buscarValor(todas, "LITRO_COMBUSTIBLE");
        BigDecimal gestion = buscarValor(todas, "CARGO_GESTION_TRAMO");
        BigDecimal estadia = buscarValor(todas, "COSTO_ESTADIA_DIARIO");
        BigDecimal baseBajo = buscarValor(todas, "BASE_VOLUMEN_BAJO");
        BigDecimal baseAlto = buscarValor(todas, "BASE_VOLUMEN_ALTO");

        return TarifasVigentesDto.builder()
                .precioLitroCombustible(combustible)
                .cargoGestionFijo(gestion)
                .costoEstadiaDiario(estadia)
                .costoBaseKmMuyBajo(baseBajo)
                .costoBaseKmAlto(baseAlto)
                .build();
    }

    // Método auxiliar para filtrar la lista
    private BigDecimal buscarValor(List<Tarifa> tarifas, String tipo) {
        return tarifas.stream()
                .filter(t -> t.getTipo().equalsIgnoreCase(tipo))
                .findFirst()
                .map(Tarifa::getValor)
                .orElse(BigDecimal.ZERO); // Valor por defecto si no está configurada
    }
}
