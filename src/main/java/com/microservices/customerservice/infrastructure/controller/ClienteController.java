package com.microservices.customerservice.infrastructure.controller;

import com.microservices.customerservice.application.service.ClienteService;
import com.microservices.customerservice.domain.dto.ClienteDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
@Slf4j
@CrossOrigin(origins = "*")
public class ClienteController {

    private final ClienteService clienteService;

    @PostMapping
    public ResponseEntity<ClienteDto> crearCliente(@Valid @RequestBody ClienteDto clienteDto) {
        log.info("POST /api/clientes - Creando cliente: {}", clienteDto.getIdentificacion());
        ClienteDto clienteCreado = clienteService.crearCliente(clienteDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDto>> obtenerClientes(
            @RequestParam(required = false) Boolean activos) {
        log.info("GET /api/clientes - Obteniendo clientes");
        List<ClienteDto> clientes;
        
        if (Boolean.TRUE.equals(activos)) {
            clientes = clienteService.obtenerClientesActivos();
        } else {
            clientes = clienteService.obtenerTodosLosClientes();
        }
        
        return ResponseEntity.ok(clientes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClienteDto> obtenerClientePorId(@PathVariable Long id) {
        log.info("GET /api/clientes/{} - Obteniendo cliente por ID", id);
        ClienteDto cliente = clienteService.obtenerClientePorId(id);
        return ResponseEntity.ok(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClienteDto> actualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteDto clienteDto) {
        log.info("PUT /api/clientes/{} - Actualizando cliente", id);
        ClienteDto clienteActualizado = clienteService.actualizarCliente(id, clienteDto);
        return ResponseEntity.ok(clienteActualizado);
    }


    @PatchMapping("/{id}/desactivar")
    public ResponseEntity<ClienteDto> desactivarCliente(@PathVariable Long id) {
        log.info("PATCH /api/clientes/{}/desactivar - Desactivando cliente", id);
        ClienteDto clienteDesactivado = clienteService.desactivarCliente(id);
        return ResponseEntity.ok(clienteDesactivado);
    }
}
