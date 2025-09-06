package com.microservices.customerservice.domain.mapper;

import com.microservices.customerservice.domain.dto.ClienteDto;
import com.microservices.customerservice.domain.entity.Cliente;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClienteMapper {

    public Cliente toEntity(ClienteDto clienteDto) {
        if (clienteDto == null) {
            return null;
        }

        Cliente cliente = new Cliente();
        cliente.setId(clienteDto.getId());
        cliente.setContrasena(clienteDto.getContrasena());
        cliente.setEstado(clienteDto.getEstado());

        // Establecer campos de Persona directamente en Cliente
        cliente.setNombre(clienteDto.getNombre());
        cliente.setGenero(clienteDto.getGenero());
        cliente.setEdad(clienteDto.getEdad());
        cliente.setIdentificacion(clienteDto.getIdentificacion());
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setTelefono(clienteDto.getTelefono());

        return cliente;
    }

    public ClienteDto toDto(Cliente cliente) {
        if (cliente == null) {
            return null;
        }

        ClienteDto dto = new ClienteDto();
        dto.setId(cliente.getId());
        dto.setContrasena(cliente.getContrasena());
        dto.setEstado(cliente.getEstado());

        // Obtener campos de Persona directamente de Cliente
        dto.setNombre(cliente.getNombre());
        dto.setGenero(cliente.getGenero());
        dto.setEdad(cliente.getEdad());
        dto.setIdentificacion(cliente.getIdentificacion());
        dto.setDireccion(cliente.getDireccion());
        dto.setTelefono(cliente.getTelefono());

        return dto;
    }

    public List<ClienteDto> toDtoList(List<Cliente> clientes) {
        if (clientes == null) {
            return null;
        }
        return clientes.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public void updateEntity(ClienteDto clienteDto, Cliente cliente) {
        if (clienteDto == null || cliente == null) {
            return;
        }

        cliente.setContrasena(clienteDto.getContrasena());
        cliente.setEstado(clienteDto.getEstado());

        // Actualizar campos de Persona directamente en Cliente
        cliente.setNombre(clienteDto.getNombre());
        cliente.setGenero(clienteDto.getGenero());
        cliente.setEdad(clienteDto.getEdad());
        cliente.setIdentificacion(clienteDto.getIdentificacion());
        cliente.setDireccion(clienteDto.getDireccion());
        cliente.setTelefono(clienteDto.getTelefono());
    }
}