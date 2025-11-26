// package com.backend.logistica.services;

// import com.backend.logistica.entities.Solicitud;
// import com.backend.logistica.entities.Tramo;
// import com.backend.logistica.entities.dto.external.DistanciaDto;
// import com.backend.logistica.entities.dto.external.TarifasVigentesDto;
// import com.backend.logistica.repositories.SolicitudRepositoryImpl;
// import com.backend.logistica.repositories.TramoRepositoryImpl;
// import lombok.RequiredArgsConstructor;
// import org.springframework.beans.factory.annotation.Qualifier;
// import org.springframework.stereotype.Service;
// import org.springframework.transaction.annotation.Transactional;
// import org.springframework.web.client.RestClient;
// import java.math.BigDecimal;
// import java.math.RoundingMode;
// import java.util.List;

// @Service
// @RequiredArgsConstructor
// public class CalculoCostosService {

//     private final SolicitudRepositoryImpl solicitudRepository;
//     private final TramoRepositoryImpl tramoRepository;

//     @Qualifier("geoApiRestClient")
//     private final RestClient geoApiClient;

//     @Qualifier("gestionRestClient")
//     private final RestClient gestionClient;

//     /**
//      * Calcula costos estimados para una solicitud que ya tiene ruta asignada.
//      */
//     @Transactional
//     public void calcularEstimado(Long idSolicitud) {
//         Solicitud solicitud = solicitudRepository.findById(idSolicitud)
//             .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

//         if (solicitud.getRutaAsignada() == null) {
//             throw new RuntimeException("La solicitud no tiene ruta asignada, no se puede calcular.");
//         }

//         // 1. Obtener Tarifas Vigentes de Gestión
//         // (Asegúrate de tener este endpoint creado en Gestión o usa valores mock por ahora)
//         TarifasVigentesDto tarifas = obtenerTarifasDeGestion();
        
//         // 2. Obtener Consumo Promedio (Simulado o de Gestión)
//         BigDecimal consumoPromedio = new BigDecimal("0.35"); // Litros por Km (Promedio)

//         // 3. Determinar Costo Base por Km según volumen
//         BigDecimal volumen = solicitud.getContenedor().getVolumen();
//         BigDecimal costoBaseKm = (volumen.doubleValue() > 25) 
//                 ? tarifas.getCostoBaseKmAlto() 
//                 : tarifas.getCostoBaseKmMuyBajo();

//         BigDecimal costoTotal = BigDecimal.ZERO;
//         int tiempoTotalMinutos = 0;

//         List<Tramo> tramos = tramoRepository.findByRuta(solicitud.getRutaAsignada());

//         for (Tramo tramo : tramos) {
//             // 4. Consultar Distancia a GeoAPI
//             DistanciaDto dist = obtenerDistanciaGeoApi(tramo.getOrigen(), tramo.getDestino());
            
//             // tramo.setDistanciaKm(dist.getKilometros());
//             // Convertir duración texto a minutos (simplificado: asume que viene en minutos o parsear)
//             int minutosTramo = parsearDuracion(dist.getDuracionTexto()); 
//             // tramo.setTiempoMinutos(minutosTramo);

//             // 5. FÓRMULA DE COSTO ESTIMADO
//             // Costo = (CostoBaseKm * km) + (ConsumoPromedio * km * PrecioCombustible)
//             // BigDecimal costoDistancia = costoBaseKm.multiply(BigDecimal.valueOf(tramo.getDistanciaKm()));
            
//             BigDecimal costoCombustible = consumoPromedio
//                     // .multiply(BigDecimal.valueOf(tramo.getDistanciaKm()))
//                     .multiply(tarifas.getPrecioLitroCombustible());
            
//             // Sumar cargo de gestión fijo por tramo
//             // BigDecimal costoTramo = costoDistancia
//                     .add(costoCombustible)
//                     .add(tarifas.getCargoGestionFijo());

//             // Guardar en el tramo
//             tramo.setCostoAproximado(costoTramo.doubleValue());
//             tramoRepository.save(tramo);

//             // Acumular totales
//             costoTotal = costoTotal.add(costoTramo);
//             tiempoTotalMinutos += minutosTramo;
//         }

//         // 6. Actualizar Solicitud
//         solicitud.setCostoEstimado(costoTotal.setScale(2, RoundingMode.HALF_UP));
//         solicitud.setTiempoEstimado(tiempoTotalMinutos);
//         solicitudRepository.save(solicitud);
//     }

//     // --- Métodos Auxiliares de Llamada ---

//     private TarifasVigentesDto obtenerTarifasDeGestion() {
//         // Llama al endpoint que creamos antes en Gestión
//         return gestionClient.get()
//             .uri("/api/v1/gestion/tarifa/vigente") 
//             .retrieve()
//             .body(TarifasVigentesDto.class);
//     }

//     private DistanciaDto obtenerDistanciaGeoApi(String origen, String destino) {
//         // Llama a tu GeoAPI
//         return geoApiClient.get()
//             .uri(uriBuilder -> uriBuilder
//                 .path("/api/distancia")
//                 .queryParam("origen", origen)
//                 .queryParam("destino", destino)
//                 .build())
//             .retrieve()
//             .body(DistanciaDto.class);
//     }
    
//     private int parsearDuracion(String texto) {
//         // Lógica simple para parsear "1 hour 30 mins" o lo que devuelva tu API
//         // Por ahora devolvemos un valor fijo para no complicar si el formato es complejo
//         return 60; 
//     }
// }