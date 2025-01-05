package com.aluracursos.forohub.persistence.repository;

import com.aluracursos.forohub.persistence.entity.Tema;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemaRepository extends JpaRepository<Tema, Long> {

  List<Tema> findAllByUserId(Long userId);
}
