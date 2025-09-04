package com.microservices.customerservice.domain.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDto {

    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100, message = "El nombre no puede exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "El género es obligatorio")
    @Pattern(regexp = "^(M|F)$", message = "El género debe ser M o F")
    private String genero;

    @NotNull(message = "La edad es obligatoria")
    @Min(value = 18, message = "La edad mínima es 18 años")
    @Max(value = 120, message = "La edad máxima es 120 años")
    private Integer edad;

    @NotBlank(message = "La identificación es obligatoria")
    @Size(max = 20, message = "La identificación no puede exceder 20 caracteres")
    private String identificacion;

    @NotBlank(message = "La dirección es obligatoria")
    @Size(max = 200, message = "La dirección no puede exceder 200 caracteres")
    private String direccion;

    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{10}$", message = "El teléfono debe tener 10 dígitos")
    private String telefono;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, max = 20, message = "La contraseña debe tener entre 4 y 20 caracteres")
    private String contrasena;

    @NotNull(message = "El estado es obligatorio")
    private Boolean estado;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}

