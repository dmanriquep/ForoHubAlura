package com.aluracursos.forohub.service.impl;

import com.aluracursos.forohub.exceptions.TopicNotFoundException;
import com.aluracursos.forohub.exceptions.UserNotFoundException;
import com.challenge.forochallenge.persistence.dto.topico.request.TopicRequest;
import com.challenge.forochallenge.persistence.dto.topico.response.TopicResponse;
import com.aluracursos.forohub.persistence.entity.Tema;
import com.aluracursos.forohub.persistence.entity.Usuario;
import com.aluracursos.forohub.persistence.mapper.TemaMapper;
import com.aluracursos.forohub.persistence.repository.TemaRepository;
import com.aluracursos.forohub.persistence.repository.UsuarioRepository;
import com.aluracursos.forohub.service.ITopicService;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TopicServiceImpl implements ITopicService {

  private final TemaRepository topicRepository;
  private final UsuarioRepository userRepository;

  @Autowired
  public TopicServiceImpl(TemaRepository topicRepository, UsuarioRepository userRepository) {
    this.topicRepository = topicRepository;
    this.userRepository = userRepository;
  }

  @Override
  public TopicResponse createTopic(Long userId, TopicRequest request) {
    Usuario userExists = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not found"));
    Tema topic = TemaMapper.toTopicEntity(request);
    topic.setUser(userExists);
    return TemaMapper.toTopicDto(topicRepository.save(topic));
  }

  @Override
  public TopicResponse getTopicById(Long topicId) {
    return TemaMapper.toTopicDto(topicRepository.findById(topicId)
        .orElseThrow(() -> new TopicNotFoundException("No hay temas para este usuario")));
  }

  @Override
  public List<TopicResponse> getAllTopicsByUser(Long userId) {
    List<Tema> topics = topicRepository.findAllByUserId(userId);
    return TemaMapper.toTopicDtoList(topics);
  }

  @Override
  public List<TopicResponse> getAllTopics() {
    return TemaMapper.toTopicDtoList(topicRepository.findAll());
  }

  @Override
  @Transactional
  public TopicResponse updateTopic(Long userId, Long topicId, TopicRequest request) {
    Usuario userExists = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not Found"));
    Tema topicExists = topicRepository.findById(topicId)
        .orElseThrow(() -> new TopicNotFoundException("Topic not found"));
    if (!Objects.equals(userExists.getId(), topicExists.getUser().getId())) {
      throw new IllegalArgumentException("Este tema pertenece a otro usuario");
    }
    TemaMapper.updateTopicEntity(topicExists, request);
    return TemaMapper.toTopicDto(topicRepository.save(topicExists));
  }

  @Override
  @Transactional
  public com.challenge.forochallenge.utils.EliminarRespuesta deleteTopic(Long topicId, Long userId) {
    Usuario userExists = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("User not Found"));
    Tema topicExists = topicRepository.findById(topicId)
        .orElseThrow(() -> new TopicNotFoundException("Topic not found"));
    if (!Objects.equals(userExists.getId(), topicExists.getUser().getId())) {
      throw new IllegalArgumentException("Este tema pertenece a otro usuario");
    }
    topicRepository.delete(topicExists);
    return new com.challenge.forochallenge.utils.EliminarRespuesta("Tema eliminado correctamente");
  }
}
