package com.aluracursos.forohub.persistence.repository;

import com.aluracursos.forohub.persistence.entity.Usuario;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

  Optional<Usuario> findByUsernameIgnoreCase(String username);
}
