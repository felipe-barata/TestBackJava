package br.com.santander.gastosapi.controller;

import br.com.santander.gastosapi.domain.Sistema;
import br.com.santander.gastosapi.dtos.CadastroSistemaDTO;
import br.com.santander.gastosapi.response.Response;
import br.com.santander.gastosapi.services.SistemaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/sistema")
@CrossOrigin(origins = "*")
@Slf4j
public class SistemaController {

  @Autowired
  private SistemaService sistemaService;

  @Secured("ROLE_ADMIN")
  @PostMapping(value = "incluir", consumes = "application/json", produces = "application/json")
  public ResponseEntity incluirSistema(@RequestBody @Valid CadastroSistemaDTO dto, BindingResult bindingResult) {
    log.info("incluirSistema");
    Response response = new Response();
    List<String> erros = new ArrayList<>();
    if (bindingResult != null && bindingResult.hasErrors()) {
      log.warn("incluirSistema - erros de validacao");
      response.setErrors(new ArrayList<>());
      bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
      return ResponseEntity.badRequest().body(response);
    }

    Optional<Sistema> optSistema = sistemaService.incluiSistemaEAtribuiToken(convertSistemaDto(dto), dto.getChaveToken());
    if (optSistema.isPresent()) {
      response.setData(optSistema.get());
    } else {
      erros.add("Erro incluir sistema");
    }

    response.setErrors(erros);

    return ResponseEntity.ok(response);
  }

  private Sistema convertSistemaDto(CadastroSistemaDTO dto) {
    return Sistema.builder()
        .codigoSistema(dto.getCodigoSistema())
        .nome(dto.getNome())
        .build();
  }
}
