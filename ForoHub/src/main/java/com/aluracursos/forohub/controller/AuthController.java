package com.aluracursos.forohub.controller;

import com.challenge.forochallenge.persistence.dto.auth.request.LoginRequest;
import com.challenge.forochallenge.persistence.dto.auth.request.RegisterRequest;
import com.challenge.forochallenge.persistence.dto.auth.response.AuthResponse;
import com.challenge.forochallenge.persistence.dto.auth.response.RegisterResponse;
import com.aluracursos.forohub.service.IAuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

  private final IAuthService authService;

  @Autowired
  public AuthController(IAuthService authService) {
    this.authService = authService;
  }

  @PostMapping("/login")
  public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest request) {
    return ResponseEntity.ok(authService.login(request));
  }

  @PostMapping("/register")
  public ResponseEntity<RegisterResponse> register(@RequestBody @Valid RegisterRequest request) {
    return ResponseEntity.status(HttpStatus.CREATED).body(authService.register(request));
  }
}
