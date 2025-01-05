package com.challenge.forochallenge.persistence.dto.auth.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterRequest(
    @NotBlank(message = "El name no puede ir vacio")
    @Size(min = 5, max = 50)
    String name,
    @NotBlank(message = "El username no puede ir vacio")
    @Email(message = "El username debe ser un correo electronico")
    String username,
    @NotBlank(message = "El password no puede ir vacio")
    @Size(min = 5, max = 50)
    String password
) {

}
