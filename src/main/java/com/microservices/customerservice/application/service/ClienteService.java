package com.microservices.customerservice.application.service;

import com.microservices.customerservice.domain.dto.ClienteDto;
import com.microservices.customerservice.domain.entity.Cliente;
import com.microservices.customerservice.domain.exception.ClienteAlreadyExistsException;
import com.microservices.customerservice.domain.exception.ClienteNotFoundException;
import com.microservices.customerservice.domain.mapper.ClienteMapper;
import com.microservices.customerservice.domain.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final ClienteMapper clienteMapper;

    public ClienteDto crearCliente(ClienteDto clienteDto) {
        log.info("Creando cliente con identificación: {}", clienteDto.getIdentificacion());
        
        if (clienteRepository.existsByIdentificacion(clienteDto.getIdentificacion())) {
            throw new ClienteAlreadyExistsException(
                "Ya existe un cliente con la identificación: " + clienteDto.getIdentificacion()
            );
        }

        Cliente cliente = clienteMapper.toEntity(clienteDto);
        Cliente clienteGuardado = clienteRepository.save(cliente);
        
        log.info("Cliente creado exitosamente con ID: {}", clienteGuardado.getId());
        return clienteMapper.toDto(clienteGuardado);
    }

    @Transactional(readOnly = true)
    public List<ClienteDto> obtenerTodosLosClientes() {
        log.info("Obteniendo todos los clientes");
        List<Cliente> clientes = clienteRepository.findAll();
        return clienteMapper.toDtoList(clientes);
    }

    @Transactional(readOnly = true)
    public List<ClienteDto> obtenerClientesActivos() {
        log.info("Obteniendo clientes activos");
        List<Cliente> clientes = clienteRepository.findAllActiveClients();
        return clienteMapper.toDtoList(clientes);
    }

    @Transactional(readOnly = true)
    public ClienteDto obtenerClientePorId(Long id) {
        log.info("Obteniendo cliente por ID: {}", id);
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con ID: " + id));
        return clienteMapper.toDto(cliente);
    }

    @Transactional(readOnly = true)
    public ClienteDto obtenerClientePorIdentificacion(String identificacion) {
        log.info("Obteniendo cliente por identificación: {}", identificacion);
        Cliente cliente = clienteRepository.findByIdentificacion(identificacion)
            .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con identificación: " + identificacion));
        return clienteMapper.toDto(cliente);
    }

    public ClienteDto actualizarCliente(Long id, ClienteDto clienteDto) {
        log.info("Actualizando cliente con ID: {}", id);
        
        Cliente clienteExistente = clienteRepository.findById(id)
            .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con ID: " + id));

        // Verificar si la identificación ya existe en otro cliente
        if (!clienteExistente.getIdentificacion().equals(clienteDto.getIdentificacion()) &&
            clienteRepository.existsByIdentificacion(clienteDto.getIdentificacion())) {
            throw new ClienteAlreadyExistsException(
                "Ya existe un cliente con la identificación: " + clienteDto.getIdentificacion()
            );
        }

        clienteMapper.updateEntity(clienteDto, clienteExistente);
        Cliente clienteActualizado = clienteRepository.save(clienteExistente);
        
        log.info("Cliente actualizado exitosamente con ID: {}", id);
        return clienteMapper.toDto(clienteActualizado);
    }


    public ClienteDto desactivarCliente(Long id) {
        log.info("Desactivando cliente con ID: {}", id);
        
        Cliente cliente = clienteRepository.findById(id)
            .orElseThrow(() -> new ClienteNotFoundException("Cliente no encontrado con ID: " + id));
        
        cliente.setEstado(false);
        Cliente clienteActualizado = clienteRepository.save(cliente);
        
        log.info("Cliente desactivado exitosamente con ID: {}", id);
        return clienteMapper.toDto(clienteActualizado);
    }
}

