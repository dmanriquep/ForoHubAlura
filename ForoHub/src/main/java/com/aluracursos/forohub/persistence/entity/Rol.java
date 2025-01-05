package com.aluracursos.forohub.persistence.entity;

import java.util.Arrays;
import java.util.List;
import lombok.Getter;

@Getter
public enum Rol {
  ADMIN(Arrays.asList(Permisos.values())),
  USER(Arrays.asList(Permisos.values()));

  private final List<Permisos> permissions;

  Rol(List<Permisos> permissions) {
    this.permissions = permissions;
  }
}
