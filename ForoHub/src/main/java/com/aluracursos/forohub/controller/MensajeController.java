package com.aluracursos.forohub.controller;

import com.aluracursos.forohub.persistence.entity.Usuario;
import com.aluracursos.forohub.service.IMessageService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
@PreAuthorize("isAuthenticated()")
@SecurityRequirement(name = "bearer-key")
public class MensajeController {

  private final IMessageService messageService;

  @Autowired
  public MensajeController(IMessageService messageService) {
    this.messageService = messageService;
  }

  @GetMapping("/all")
  public ResponseEntity<List<com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje>> findAll() {
    return ResponseEntity.ok(messageService.allMessages());
  }

  @GetMapping("/me")
  public ResponseEntity<List<com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje>> findByUser(
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = ((Usuario) userDetails).getId();
    return ResponseEntity.ok(messageService.allMessagesByUser(userId));
  }

  @GetMapping("/{topicId}")
  public ResponseEntity<List<com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje>> findByTopic(@PathVariable Long topicId) {
    return ResponseEntity.ok(messageService.allMessagesByTopic(topicId));
  }

  @PostMapping
  public ResponseEntity<com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje> create(
      @RequestBody @Valid com.challenge.forochallenge.persistence.dto.message.request.SolicitudMensaje request,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = ((Usuario) userDetails).getId();
    com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje response = messageService.createMessage(request, userId);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
  }

  @PutMapping("/{messageId}")
  public ResponseEntity<com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje> update(
      @PathVariable Long messageId,
      @RequestBody @Valid com.challenge.forochallenge.persistence.dto.message.request.ActualizaSolicitudMensaje request,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = ((Usuario) userDetails).getId();
    com.challenge.forochallenge.persistence.dto.message.response.RespuestaMensaje response = messageService.updateMessage(request, messageId, userId);
    return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
  }

  @DeleteMapping("/{messageId}")
  public ResponseEntity<com.challenge.forochallenge.utils.EliminarRespuesta> delete(
      @PathVariable Long messageId,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = ((Usuario) userDetails).getId();
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(messageService.deleteMessage(userId, messageId));
  }
}
