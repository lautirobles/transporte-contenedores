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

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import com.backend.logistica.mapper.SolicitudMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.backend.logistica.clients.ClientesClient;
import com.backend.logistica.entities.dto.ClienteDTO;
import com.backend.logistica.entities.Contenedor;
import com.backend.logistica.entities.dto.ContenedorDto;

/////////////////////////////////////////////////////////////////////////////////// 
import com.backend.logistica.repositories.CambioEstadoRepositoryImpl;
import com.backend.logistica.entities.CambioEstado;
import com.backend.logistica.entities.dto.SeguimientoSolicitudDto;
import com.backend.logistica.entities.dto.HistorialEstadoDto;
import java.util.ArrayList;


@Service
@RequiredArgsConstructor
public class SolicitudServiceImpl implements SolicitudService {
    
    private final SolicitudRepositoryImpl solicitudRepository;
    private final RutaRepositoryImpl rutaRepository;
    private final ContenedorRepositoryImpl contenedorRepository;
    
    private final CambioEstadoRepositoryImpl cambioEstadoRepository;
    
    @Autowired
    private ClientesClient clientesClient;

    @Override
    public List<SolicitudDto> getAllSolicitudes(){
        List<Solicitud> solicitudes = solicitudRepository.findAll();
        // Para cada solicitud, debemos obtener su cliente y contenedor para poder mapearla a DTO.
        return solicitudes.stream().map(solicitud -> {
            try {
                ClienteDTO clienteDTO = clientesClient.obtenerPorId(solicitud.getCliente());
                Contenedor contenedor = solicitud.getContenedor();
                return SolicitudMapper.entityToDto(solicitud, clienteDTO, contenedor);
            } catch (Exception e) {
                System.out.println("⚠️ ERROR al procesar solicitud: " + e.getMessage());
                return null;
            }
        }).filter(dto -> dto != null).collect(Collectors.toList());
    }

    @Override
    public SolicitudDto getSolicitud(Long numero){
        Solicitud solicitud = solicitudRepository.findById(numero)
                .orElseThrow(() -> new NoSuchElementException("No se encontró solicitud con id: " + numero));

        // Obtenemos los datos adicionales necesarios para el mapper
        ClienteDTO clienteDTO = clientesClient.obtenerPorId(solicitud.getCliente());
        Contenedor contenedor = solicitud.getContenedor();

        return SolicitudMapper.entityToDto(solicitud, clienteDTO, contenedor);
    }

    @Override
    public SolicitudDto createSolicitud(SolicitudDto dto){
        Solicitud solicitud = SolicitudMapper.dtoToEntity(dto);

        // Validamos y obtenemos el cliente
        ClienteDTO clienteDTO = clientesClient.obtenerPorId(dto.getClienteId());

        // Validamos y obtenemos el contenedor
        Contenedor contenedor = contenedorRepository.findById(dto.getContenedorId())
                .orElseThrow(() -> new NoSuchElementException("No se encontró contenedor con id: " + dto.getContenedorId()));
        
        // Asignamos las entidades a la solicitud
        solicitud.setCliente(clienteDTO.getId());
        solicitud.setContenedor(contenedor);
        
        // Si viene una ruta, la asignamos
        if (dto.getRutaAsignada() != null) {
            Ruta ruta = rutaRepository.findById(dto.getRutaAsignada())
                    .orElseThrow(() -> new NoSuchElementException("No se encontró ruta con id: " + dto.getRutaAsignada()));
            solicitud.setRutaAsignada(ruta);
        }

        solicitudRepository.save(solicitud);
        return SolicitudMapper.entityToDto(solicitud, clienteDTO, contenedor);
    }

    @Override
    public SolicitudDto updateSolicitud(Long numero, Solicitud solicitud){
        if(solicitudRepository.existsById(numero)){
            solicitud.setNumero(numero);
            Solicitud solicitudGuardada = solicitudRepository.save(solicitud);

            // Obtenemos los datos para el DTO
            ClienteDTO clienteDTO = clientesClient.obtenerPorId(solicitudGuardada.getCliente());
            Contenedor contenedor = solicitudGuardada.getContenedor();

            return SolicitudMapper.entityToDto(solicitudGuardada, clienteDTO, contenedor);
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
        Ruta ruta = rutaRepository.findById(rutaAsignada.getId()).orElse(null);
        solicitud.setRutaAsignada(ruta);
        solicitudRepository.save(solicitud);
    
    }

    @Override
    public void deleteSolicitud(Long numero){
        if(!solicitudRepository.existsById(numero)){
            throw new NoSuchElementException("No existe una solicitud con id: " + numero);
        }else{
            solicitudRepository.deleteById(numero);
        }


    }

    // crear solicitud con id cliente como requestParam y validar existencia del cliente via restclient, si no existe crearlo en gestion y luego
    // asignarlo a la solicitud. tambien recibe id contenedor para crearlo y asignarlo a la solicitud.

    @Override
    public SolicitudDto createSolicitudConClienteYContenedor(SolicitudDto dto) {

        // Validar existencia del cliente via RestClient
        ClienteDTO clienteDTO;
        try {
            // Si existe, obtener el cliente
            clienteDTO = clientesClient.obtenerPorId(dto.getClienteId());
        } catch (NoSuchElementException e) {
            // Si no existe, crearlo usando los datos del DTO de la solicitud.
            // El clienteDTO viene dentro de SolicitudDto.
            clienteDTO = clientesClient.crear(dto.getCliente());
        }



        // Crear y asignar el contenedor
        ContenedorDto contenedorDto = dto.getContenedor();
        Contenedor contenedor = new Contenedor();
        contenedor.setPeso(contenedorDto.getPeso());
        contenedor.setVolumen(contenedorDto.getVolumen());
        contenedor.setEstado("CREADO");
        // Asociamos el contenedor al ID del cliente (ya sea el que existía o el nuevo)
        contenedor.setClienteAsociado(clienteDTO.getId());

        contenedorRepository.save(contenedor);

        // Crear la solicitud y asignar el cliente y contenedor
        Solicitud solicitud = SolicitudMapper.dtoToEntity(dto);
        solicitud.setCliente(clienteDTO.getId());
        solicitud.setContenedor(contenedor);
        solicitudRepository.save(solicitud);

        return SolicitudMapper.entityToDto(solicitud, clienteDTO, contenedor);
    }


    ///////////// seguimiento de solicitud ////////////////
    @Override
    public SeguimientoSolicitudDto obtenerSeguimiento(Long numero) {
        // 1. Buscar la solicitud
        Solicitud solicitud = solicitudRepository.findById(numero)
            .orElseThrow(() -> new NoSuchElementException("No se encontró la solicitud con id: " + numero));

        // 2. Obtener el historial del contenedor asociado
        List<HistorialEstadoDto> historialDto = new ArrayList<>();
        
        if (solicitud.getContenedor() != null) {
            List<CambioEstado> cambios = cambioEstadoRepository
                .findByContenedorOrderByFechaCambioDesc(solicitud.getContenedor());
            
            // Mapear entidades a DTOs
            historialDto = cambios.stream()
                .map(c -> HistorialEstadoDto.builder()
                    .estadoAnterior(c.getEstadoAnterior())
                    .estadoNuevo(c.getEstadoNuevo())
                    .fechaCambio(c.getFechaCambio())
                    .build())
                .collect(Collectors.toList());
        }

        // 3. Construir y retornar la respuesta completa
        return SeguimientoSolicitudDto.builder()
            .numeroSolicitud(solicitud.getNumero())
            .estadoActual(solicitud.getEstado()) // El estado actual de la solicitud
            .contenedorId(solicitud.getContenedor() != null ? solicitud.getContenedor().getId() : null)
            .costoEstimado(solicitud.getCostoEstimado())
            .tiempoEstimado(solicitud.getTiempoEstimado())
            .historialEstados(historialDto)
            .build();
    }
}
