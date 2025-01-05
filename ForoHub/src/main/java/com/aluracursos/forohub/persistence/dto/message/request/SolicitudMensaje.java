package com.challenge.forochallenge.persistence.dto.message.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record SolicitudMensaje(
    @NotBlank
    @Size(min = 4, max = 50, message = "Minimo de 4 caracteres y maximo de 50 caracteres")
    String content,
    @JsonProperty("topic_id")
    Long topicId
) {

}
