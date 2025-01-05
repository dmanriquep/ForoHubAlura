package com.aluracursos.forohub.service;

import com.challenge.forochallenge.persistence.dto.topico.request.TopicRequest;
import com.challenge.forochallenge.persistence.dto.topico.response.TopicResponse;

import java.util.List;

public interface ITopicService {

  TopicResponse createTopic(Long userId, TopicRequest request);

  TopicResponse getTopicById(Long topicId);

  List<TopicResponse> getAllTopicsByUser(Long userId);

  List<TopicResponse> getAllTopics();

  TopicResponse updateTopic(Long userId, Long topicId, TopicRequest request);

  com.challenge.forochallenge.utils.EliminarRespuesta deleteTopic(Long topicId, Long userId);
}
