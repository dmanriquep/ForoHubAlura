package com.aluracursos.forohub.persistence.mapper;

import com.challenge.forochallenge.persistence.dto.topico.request.TopicRequest;
import com.challenge.forochallenge.persistence.dto.topico.response.TopicResponse;
import com.aluracursos.forohub.persistence.entity.Tema;
import com.aluracursos.forohub.persistence.entity.EstadoTema;
import java.util.List;

public class TemaMapper {

  public static TopicResponse toTopicDto(Tema topic) {
    if (topic == null) {
      return null;
    }
    return new TopicResponse(
        topic.getId(),
        topic.getTitle(),
        topic.getCurso(),
        topic.getCreatedAt(),
        UsuarioMapper.toUserDto(topic.getUser()),
        MensajeMapper.toMessageDtoList(topic.getMessages())
    );
  }

  public static List<TopicResponse> toTopicDtoList(List<Tema> topics) {
    if (topics == null) {
      return null;
    }

    return topics.stream()
        .map(TemaMapper::toTopicDto)
        .toList();
  }

  public static Tema toTopicEntity(TopicRequest topicDto) {
    if (topicDto == null) {
      return null;
    }

    Tema topic = new Tema();
    topic.setTitle(topicDto.title());
    topic.setCurso(topicDto.curso());
    topic.setStatus(EstadoTema.PUBLIC);

    return topic;
  }

  public static void updateTopicEntity(Tema oldTopic, TopicRequest topicDto) {
    if (oldTopic != null && topicDto != null) {
      if (topicDto.title() != null) {
        oldTopic.setTitle(topicDto.title());
      }
      if (topicDto.curso() != null) {
        oldTopic.setCurso(topicDto.curso());
      }
    }
  }
}
