package com.backend.logistica.mapper;

import org.springframework.stereotype.Component;

import com.backend.logistica.entities.Contenedor;
import com.backend.logistica.entities.dto.ContenedorDto;

@Component
public class ContenedorMapper {

	public static Contenedor dtoToEntity(ContenedorDto dto) {
		if (dto == null) return null;

		return Contenedor.builder()
				.id(dto.getId())
				.peso(dto.getPeso())
				.volumen(dto.getVolumen())
				.estado(dto.getEstado())
				.clienteAsociado(dto.getClienteAsociado())
				.build();
	}

	public static ContenedorDto entityToDto(Contenedor entity) {
		if (entity == null) return null;

		return new ContenedorDto(
				entity.getId(),
				entity.getPeso(),
				entity.getVolumen(),
				entity.getEstado(),
				entity.getClienteAsociado()
		);
	}

}
