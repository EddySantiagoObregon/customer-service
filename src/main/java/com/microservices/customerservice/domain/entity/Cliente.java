package com.microservices.customerservice.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "clientes")
@PrimaryKeyJoinColumn(name = "persona_id")
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Cliente extends Persona {

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 4, max = 20, message = "La contraseña debe tener entre 4 y 20 caracteres")
    @Column(name = "contrasena", nullable = false, length = 20)
    private String contrasena;

    @NotNull(message = "El estado es obligatorio")
    @Column(name = "estado", nullable = false)
    private Boolean estado = true;

    public Cliente(String nombre, String genero, Integer edad, String identificacion, 
                   String direccion, String telefono, String contrasena, Boolean estado) {
        super(nombre, genero, edad, identificacion, direccion, telefono);
        this.contrasena = contrasena;
        this.estado = estado;
    }
}

