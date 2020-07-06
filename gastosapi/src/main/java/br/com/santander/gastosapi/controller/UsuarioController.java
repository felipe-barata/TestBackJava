package br.com.santander.gastosapi.controller;

import br.com.santander.gastosapi.domain.Usuario;
import br.com.santander.gastosapi.dtos.CadastraUsuarioDTO;
import br.com.santander.gastosapi.dtos.UsuarioDTO;
import br.com.santander.gastosapi.response.Response;
import br.com.santander.gastosapi.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("api/usuario")
@CrossOrigin(origins = "*")
@Slf4j
public class UsuarioController {

  @Autowired
  private UsuarioService usuarioService;

  @PutMapping(value = "atualiza")
  public ResponseEntity atualizaUsuario(@RequestBody @Valid CadastraUsuarioDTO dto, BindingResult bindingResult) {
    log.info("atualizaUsuario");
    Response response = new Response();
    response.setErrors(new ArrayList<>());
    if (bindingResult != null && bindingResult.hasErrors()) {
      log.warn("atualizaUsuario - erros de validacao");
      bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
      return ResponseEntity.badRequest().body(response);
    }
    Optional<Usuario> usuario = usuarioService.persisteUsuario(convertToUsuario(dto));
    if (usuario.isPresent()) {
      Usuario u = usuario.get();
      response.setData(UsuarioDTO.builder()
          .email(u.getEmail())
          .id(u.getId())
          .nome(u.getNome())
          .build());
      return ResponseEntity.ok(response);
    } else {
      return ResponseEntity.noContent().build();
    }
  }

  private Usuario convertToUsuario(CadastraUsuarioDTO dto) {
    return Usuario.builder()
        .senha(dto.getSenha())
        .nome(dto.getNome())
        .email(dto.getEmail())
        .build();
  }
}
