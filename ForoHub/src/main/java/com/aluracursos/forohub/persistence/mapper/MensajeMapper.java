package com.aluracursos.forohub.persistence.mapper;

import com.aluracursos.forohub.persistence.entity.Mensaje;
import java.util.List;

public class MensajeMapper {

  public static com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje toMessageDto(Mensaje message) {
    if (message == null) {
      return null;
    }

    return new com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje(
        message.getId(),
        message.getContent()
    );
  }

  public static List<com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje> toMessageDtoList(List<Mensaje> messages) {
    if (messages == null) {
      return null;
    }

    return messages.stream()
        .map(MensajeMapper::toMessageDto)
        .toList();
  }

  public static Mensaje toMessageEntity(com.challenge.forochallenge.persistence.dto.message.request.SolicitudMensaje messageDto) {
    if (messageDto == null) {
      return null;
    }

    Mensaje message = new Mensaje();
    message.setContent(messageDto.content());

    return message;
  }

  public static void updateMessageEntity(Mensaje oldMessage, com.challenge.forochallenge.persistence.dto.message.request.SolicitudMensaje messageDto) {
    if (oldMessage != null && messageDto != null) {
      oldMessage.setContent(messageDto.content());
    }
  }
}
