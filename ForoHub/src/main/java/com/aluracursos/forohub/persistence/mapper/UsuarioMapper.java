package com.aluracursos.forohub.persistence.mapper;

import com.challenge.forochallenge.persistence.dto.auth.request.RegisterRequest;
import com.aluracursos.forohub.persistence.entity.Rol;
import com.aluracursos.forohub.persistence.entity.Usuario;
import java.util.List;

public class UsuarioMapper {

  public static com.challenge.forochallenge.persistence.dto.user.response.RespuestaUsuario toUserDto(Usuario user) {
    if (user == null) {
      return null;
    }

    return new com.challenge.forochallenge.persistence.dto.user.response.RespuestaUsuario(
        user.getId(),
        user.getUsername()
    );
  }

  public static List<com.challenge.forochallenge.persistence.dto.user.response.RespuestaUsuario> toUserDtoList(List<Usuario> users) {
    if (users == null) {
      return null;
    }

    return users.stream()
        .map(UsuarioMapper::toUserDto)
        .toList();
  }

  public static Usuario toUserEntity(RegisterRequest userDto) {
    if (userDto == null) {
      return null;
    }

    Usuario user = new Usuario();
    user.setName(userDto.name());
    user.setUsername(userDto.username());
    user.setPassword(userDto.password());
    user.setRole(Rol.USER);

    return user;
  }

  public static void updateUserEntity(Usuario oldUser, com.challenge.forochallenge.persistence.dto.user.request.ActualizaSolicitudUsuario userDto) {
    if (oldUser != null && userDto != null) {
      if (userDto.name() != null) {
        oldUser.setName(userDto.name());
      }
      if (userDto.role() != null) {
        oldUser.setRole(userDto.role());
      }
      if (userDto.password() != null) {
        oldUser.setPassword(userDto.password());
      }
    }
  }
}
