package com.aluracursos.forohub.service.impl;

import com.aluracursos.forohub.exceptions.MessageNotFoundException;
import com.aluracursos.forohub.exceptions.TopicNotFoundException;
import com.aluracursos.forohub.exceptions.UserNotFoundException;
import com.aluracursos.forohub.persistence.entity.Mensaje;
import com.aluracursos.forohub.persistence.entity.Tema;
import com.aluracursos.forohub.persistence.entity.Usuario;
import com.aluracursos.forohub.persistence.mapper.MensajeMapper;
import com.aluracursos.forohub.persistence.repository.MensajeRepository;
import com.aluracursos.forohub.persistence.repository.TemaRepository;
import com.aluracursos.forohub.persistence.repository.UsuarioRepository;
import com.aluracursos.forohub.service.IMessageService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements IMessageService {

  private final UsuarioRepository userRepository;
  private final MensajeRepository messageRepository;
  private final TemaRepository topicRepository;

  @Autowired
  public MessageServiceImpl(UsuarioRepository userRepository, MensajeRepository messageRepository,
                            TemaRepository topicRepository) {
    this.userRepository = userRepository;
    this.messageRepository = messageRepository;
    this.topicRepository = topicRepository;
  }


  @Override
  public List<com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje> allMessages() {
    return MensajeMapper.toMessageDtoList(messageRepository.findAll());
  }

  @Override
  public List<com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje> allMessagesByUser(Long userId) {
    List<Mensaje> messages = messageRepository.findAllByUserId(userId);

    if (messages.isEmpty()) {
      throw new MessageNotFoundException("No hay mensajes disponibles para este usuario");
    }

    return MensajeMapper.toMessageDtoList(messages);
  }

  @Override
  public List<com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje> allMessagesByTopic(Long topicId) {
    List<Mensaje> messages = messageRepository.findAllByTopicId(topicId);

    if (messages.isEmpty()) {
      throw new MessageNotFoundException("No hay mensajes para este Tema");
    }

    return MensajeMapper.toMessageDtoList(messages);
  }

  @Override
  public com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje getMessageById(Long messageId) {
    return MensajeMapper.toMessageDto(messageRepository.findById(messageId)
        .orElseThrow(() -> new MessageNotFoundException("Message not found")));
  }

  @Override
  @Transactional
  public com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje createMessage(com.challenge.forochallenge.persistence.dto.message.request.SolicitudMensaje request, Long userId) {
    Tema topic = topicRepository.findById(request.topicId())
        .orElseThrow(() -> new TopicNotFoundException("Topic not found"));
    Usuario user = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
    Mensaje message = new Mensaje();
    message.setContent(request.content());
    message.setUser(user);
    message.setTopic(topic);
    return MensajeMapper.toMessageDto(messageRepository.save(message));
  }

  @Override
  @Transactional
  public com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje updateMessage(com.challenge.forochallenge.persistence.dto.message.request.ActualizaSolicitudMensaje request, Long messageId, Long userId) {
    Usuario userExists = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
    Mensaje messageExists = messageRepository.findById(messageId)
        .orElseThrow(() -> new MessageNotFoundException("Message not found"));

    if (!Objects.equals(userExists.getId(), messageExists.getUser().getId())) {
      throw new IllegalArgumentException("Este mensaje pertenece a otro usuario");
    }

    messageExists.setContent(request.content());
    return MensajeMapper.toMessageDto(messageRepository.save(messageExists));
  }

  @Override
  @Transactional
  public com.challenge.forochallenge.utils.EliminarRespuesta deleteMessage(Long userId, Long messageId) {
    userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
    Mensaje message = messageRepository.findById(messageId)
        .orElseThrow(() -> new MessageNotFoundException("Message not found"));
    messageRepository.delete(message);
    return new com.challenge.forochallenge.utils.EliminarRespuesta("Mensaje eliminado");
  }
}
