package com.microservices.customerservice.infrastructure.messaging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.accountingservice.domain.event.CuentaEvent;
import com.microservices.accountingservice.domain.event.MovimientoEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountingEventConsumer {

    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "cuenta-events", groupId = "customer-service-group")
    public void handleCuentaEvent(String mensaje) {
        try {
            CuentaEvent evento = objectMapper.readValue(mensaje, CuentaEvent.class);
            log.info("Evento de cuenta recibido: {} para cuenta ID: {}", 
                    evento.getEventType(), evento.getCuentaId());
            
            // Aquí se podría sincronizar información de cuentas en el servicio de clientes
            // Por ejemplo, actualizar estadísticas de cuentas por cliente
            
        } catch (Exception e) {
            log.error("Error al procesar evento de cuenta: {}", e.getMessage());
        }
    }

    @KafkaListener(topics = "movimiento-events", groupId = "customer-service-group")
    public void handleMovimientoEvent(String mensaje) {
        try {
            MovimientoEvent evento = objectMapper.readValue(mensaje, MovimientoEvent.class);
            log.info("Evento de movimiento recibido: {} para movimiento ID: {}", 
                    evento.getEventType(), evento.getMovimientoId());
            
            // Aquí se podría sincronizar información de movimientos en el servicio de clientes
            // Por ejemplo, actualizar estadísticas de transacciones por cliente
            
        } catch (Exception e) {
            log.error("Error al procesar evento de movimiento: {}", e.getMessage());
        }
    }
}
