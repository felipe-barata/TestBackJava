package br.com.santander.gastosapi.controller;

import br.com.santander.gastosapi.domain.Categoria;
import br.com.santander.gastosapi.dtos.SugestaoCategoriaDTO;
import br.com.santander.gastosapi.response.Response;
import br.com.santander.gastosapi.services.CategoriaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping("api/categoria")
@CrossOrigin(origins = "*")
@Slf4j
public class CategoriaController {

  @Autowired
  private CategoriaService categoriaService;

  @Value("${paginacao.qtd_por_pagina}")
  private int qtdPorPagina;

  @GetMapping(value = "sugerir")
  public ResponseEntity<Response> sugerirCategoria(@RequestBody @Valid SugestaoCategoriaDTO dto,
                                                   BindingResult bindingResult) {
    log.info("sugerirCategoria");
    Response response = new Response();
    response.setErrors(new ArrayList<>());
    if (bindingResult != null && bindingResult.hasErrors()) {
      log.warn("sugerirCategoria - erros de validacao");
      bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
      return ResponseEntity.badRequest().body(response);
    }

    int pagina = dto.getPagina() > 0 ? dto.getPagina() : 0;
    int qtd = dto.getQtdDeRegistros() > 0 ? dto.getQtdDeRegistros() : qtdPorPagina;

    Page<Categoria> categorias = categoriaService.sugerirCategoria(dto.getDescricao(), pagina, qtd);
    response.setData(categorias);
    return ResponseEntity.ok(response);
  }

}
