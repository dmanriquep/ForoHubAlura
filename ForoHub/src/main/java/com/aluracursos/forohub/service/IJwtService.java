package com.aluracursos.forohub.service;

import com.aluracursos.forohub.persistence.entity.Usuario;

public interface IJwtService {

  String extractUsername(String token);

  String generateToken(Usuario user);

  Boolean isTokenValid(String token, Usuario user);
}
