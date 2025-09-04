package com.microservices.customerservice.domain.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pruebas unitarias para la entidad Cliente")
class ClienteTest {

    private Cliente cliente;
    private Persona persona;

    @BeforeEach
    void setUp() {
        // Crear persona base
        persona = new Persona();
        persona.setId(1L);
        persona.setNombre("Juan Pérez");
        persona.setGenero("Masculino");
        persona.setEdad(30);
        persona.setIdentificacion("1234567890");
        persona.setDireccion("Calle Principal 123");
        persona.setTelefono("0987654321");

        // Crear cliente
        cliente = new Cliente();
        cliente.setId(1L);
        cliente.setPersona(persona);
        cliente.setClienteId("CLI001");
        cliente.setContrasena("password123");
        cliente.setEstado(true);
    }

    @Test
    @DisplayName("Debería crear un cliente válido")
    void deberiaCrearClienteValido() {
        assertNotNull(cliente);
        assertEquals(1L, cliente.getId());
        assertEquals("CLI001", cliente.getClienteId());
        assertEquals("password123", cliente.getContrasena());
        assertTrue(cliente.getEstado());
        assertNotNull(cliente.getPersona());
    }

    @Test
    @DisplayName("Debería obtener el nombre del cliente desde la persona")
    void deberiaObtenerNombreDesdePersona() {
        assertEquals("Juan Pérez", cliente.getNombre());
    }

    @Test
    @DisplayName("Debería obtener la identificación del cliente desde la persona")
    void deberiaObtenerIdentificacionDesdePersona() {
        assertEquals("1234567890", cliente.getIdentificacion());
    }

    @Test
    @DisplayName("Debería obtener la dirección del cliente desde la persona")
    void deberiaObtenerDireccionDesdePersona() {
        assertEquals("Calle Principal 123", cliente.getDireccion());
    }

    @Test
    @DisplayName("Debería obtener el teléfono del cliente desde la persona")
    void deberiaObtenerTelefonoDesdePersona() {
        assertEquals("0987654321", cliente.getTelefono());
    }

    @Test
    @DisplayName("Debería activar el cliente correctamente")
    void deberiaActivarCliente() {
        cliente.setEstado(false);
        assertFalse(cliente.getEstado());
        
        cliente.setEstado(true);
        assertTrue(cliente.getEstado());
    }

    @Test
    @DisplayName("Debería cambiar la contraseña del cliente")
    void deberiaCambiarContrasena() {
        String nuevaContrasena = "nuevaPassword456";
        cliente.setContrasena(nuevaContrasena);
        assertEquals(nuevaContrasena, cliente.getContrasena());
    }

    @Test
    @DisplayName("Debería manejar cliente con persona nula")
    void deberiaManejarClienteConPersonaNula() {
        cliente.setPersona(null);
        
        assertNull(cliente.getNombre());
        assertNull(cliente.getIdentificacion());
        assertNull(cliente.getDireccion());
        assertNull(cliente.getTelefono());
    }

    @Test
    @DisplayName("Debería validar que el cliente ID no sea nulo")
    void deberiaValidarClienteIdNoNulo() {
        assertNotNull(cliente.getClienteId());
        assertFalse(cliente.getClienteId().isEmpty());
    }

    @Test
    @DisplayName("Debería validar que la contraseña no sea nula")
    void deberiaValidarContrasenaNoNula() {
        assertNotNull(cliente.getContrasena());
        assertFalse(cliente.getContrasena().isEmpty());
    }
}