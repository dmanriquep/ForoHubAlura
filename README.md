# ForoHub Challenge Alura

Este proyecto es parte del Challenge ONE | Back End | ForoHub Alura y consiste en el desarrollo de una API REST utilizando Java y Spring Boot para gestionar un foro. El objetivo principal es implementar un sistema que permita crear, leer, actualizar y eliminar publicaciones y respuestas, además de gestionar usuarios y roles.

## Tecnologías utilizadas

Java 17
Spring Boot 3
MySQL
Hibernate (JPA)
Spring Security
Maven

## Funcionalidades principales

Gestión de publicaciones:
Crear, leer, actualizar y eliminar publicaciones.
Cada publicación incluye título, contenido, autor y fecha de creación.
Gestión de respuestas:
Crear, leer, actualizar y eliminar respuestas relacionadas con una publicación.
Cada respuesta incluye contenido, autor, fecha de creación y referencia a una publicación.

## Gestión de usuarios:

Registro de usuarios con credenciales (nombre de usuario, correo electrónico, contraseña).
Autenticación y autorización mediante Spring Security.
Asignación de roles (USER, ADMIN).

## Persistencia:

Los datos se almacenan en una base de datos MySQL.
