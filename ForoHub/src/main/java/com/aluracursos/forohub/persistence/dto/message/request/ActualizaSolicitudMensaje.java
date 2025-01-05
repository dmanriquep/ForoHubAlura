package com.challenge.forochallenge.persistence.dto.message.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ActualizaSolicitudMensaje(
    @NotBlank
    @Size(min = 4, max = 50, message = "Minimo de 4 caracteres y maximo de 50 caracteres")
    String content
) {

}
