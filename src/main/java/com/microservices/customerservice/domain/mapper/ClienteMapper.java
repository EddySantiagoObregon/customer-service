package com.microservices.customerservice.domain.mapper;

import com.microservices.customerservice.domain.dto.ClienteDto;
import com.microservices.customerservice.domain.entity.Cliente;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Cliente toEntity(ClienteDto clienteDto);

    ClienteDto toDto(Cliente cliente);

    List<ClienteDto> toDtoList(List<Cliente> clientes);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    @Mapping(target = "fechaActualizacion", ignore = true)
    Cliente updateEntity(ClienteDto clienteDto, @org.mapstruct.MappingTarget Cliente cliente);
}

