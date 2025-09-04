package com.microservices.customerservice.infrastructure.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.customerservice.domain.event.ClienteEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClienteEventProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void publishClienteEvent(ClienteEvent evento) {
        try {
            String mensaje = objectMapper.writeValueAsString(evento);
            kafkaTemplate.send("cliente-events", mensaje);
            log.info("Evento de cliente publicado: {}", evento.getEventType());
        } catch (JsonProcessingException e) {
            log.error("Error al serializar evento de cliente: {}", e.getMessage());
        }
    }
}
