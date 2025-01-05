package com.challenge.forochallenge.persistence.dto.user.request;

import com.aluracursos.forohub.persistence.entity.Rol;
import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record ActualizaSolicitudUsuario(
    @NotBlank(message = "el name no puede estar vacio")
    String name,
    @DefaultValue(value = "USER")
    Rol role,
    @NotBlank(message = "el name no puede estar vacio")
    String password
) {

}
