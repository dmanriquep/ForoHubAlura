package com.aluracursos.forohub.persistence.repository;

import com.aluracursos.forohub.persistence.entity.Mensaje;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MensajeRepository extends JpaRepository<Mensaje, Long> {

  List<Mensaje> findAllByTopicId(Long topicId);

  List<Mensaje> findAllByUserId(Long userId);
}
