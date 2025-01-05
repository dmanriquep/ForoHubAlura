package com.aluracursos.forohub.service.impl;

import com.aluracursos.forohub.exceptions.InvalidAuthException;
import com.challenge.forochallenge.persistence.dto.auth.request.LoginRequest;
import com.challenge.forochallenge.persistence.dto.auth.request.RegisterRequest;
import com.challenge.forochallenge.persistence.dto.auth.response.AuthResponse;
import com.challenge.forochallenge.persistence.dto.auth.response.RegisterResponse;
import com.aluracursos.forohub.persistence.entity.Usuario;
import com.aluracursos.forohub.persistence.mapper.UsuarioMapper;
import com.aluracursos.forohub.persistence.repository.UsuarioRepository;
import com.aluracursos.forohub.service.IAuthService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements IAuthService {

  private final UsuarioRepository userRepository;
  private final JwtServiceImpl jwtService;
  private final AuthenticationManager authenticationManager;
  private final BCryptPasswordEncoder passwordEncoder;

  @Autowired
  public AuthServiceImpl(UsuarioRepository userRepository, JwtServiceImpl jwtService,
                         AuthenticationManager authenticationManager, BCryptPasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.jwtService = jwtService;
    this.authenticationManager = authenticationManager;
    this.passwordEncoder = passwordEncoder;
  }

  @Override
  public AuthResponse login(LoginRequest request) {
    Optional<Usuario> user = userRepository.findByUsernameIgnoreCase(request.username());

    if (user.isEmpty()) {
      throw new InvalidAuthException("Usuario o Contraseña Invalidos");
    }

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(request.username(), request.password())
    );
    return new AuthResponse("Ingreso Exitoso", jwtService.generateToken(user.get()));
  }

  @Override
  public RegisterResponse register(RegisterRequest request) {
    Optional<Usuario> user = userRepository.findByUsernameIgnoreCase(request.username());
    if (user.isPresent()) {
      throw new InvalidAuthException("El usuario ya esta en uso");
    }

    Usuario createUSer = UsuarioMapper.toUserEntity(new RegisterRequest(request.name(),
        request.username(), passwordEncoder.encode(request.password())));
    userRepository.save(createUSer);

    return new RegisterResponse("Registrado correctamente");
  }

}
