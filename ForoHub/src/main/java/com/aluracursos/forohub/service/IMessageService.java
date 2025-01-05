package com.aluracursos.forohub.service;

import java.util.List;

public interface IMessageService {

  List<com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje> allMessages();

  List<com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje> allMessagesByUser(Long userId);

  List<com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje> allMessagesByTopic(Long topicId);

  com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje getMessageById(Long messageId);

  com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje createMessage(com.challenge.forochallenge.persistence.dto.message.request.SolicitudMensaje request, Long userId);

  com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje updateMessage(com.challenge.forochallenge.persistence.dto.message.request.ActualizaSolicitudMensaje request, Long messageId, Long userId);

  com.challenge.forochallenge.utils.EliminarRespuesta deleteMessage(Long userId, Long messageId);
}
