package br.com.santander.gastosapi.controller;

import br.com.santander.gastosapi.domain.Categoria;
import br.com.santander.gastosapi.domain.Gastos;
import br.com.santander.gastosapi.domain.Sistema;
import br.com.santander.gastosapi.dtos.AtualizaCategoriaDTO;
import br.com.santander.gastosapi.dtos.FiltraGastosDTO;
import br.com.santander.gastosapi.dtos.GastosDTO;
import br.com.santander.gastosapi.dtos.RetornoGastoDTO;
import br.com.santander.gastosapi.response.Response;
import br.com.santander.gastosapi.services.CategoriaService;
import br.com.santander.gastosapi.services.GastosService;
import br.com.santander.gastosapi.services.SistemaService;
import br.com.santander.gastosapi.services.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/gasto")
@CrossOrigin(origins = "*")
@Slf4j
public class GastoController {

  @Autowired
  private GastosService gastosService;

  @Autowired
  private SistemaService sistemaService;

  @Autowired
  private UsuarioService usuarioService;

  @Autowired
  private CategoriaService categoriaService;

  @Value("${paginacao.qtd_por_pagina}")
  private int qtdPorPagina;

  @PostMapping(value = "insere")
  public ResponseEntity<Response> incluirGasto(@RequestHeader(value = "token") String token, @RequestBody @Valid GastosDTO dto, BindingResult bindingResult) {
    log.info("incluirGasto");
    Response response = new Response();
    List<String> erros = new ArrayList<>();
    response.setErrors(new ArrayList<>());
    if (bindingResult != null && bindingResult.hasErrors()) {
      log.warn("incluirGasto - erros de validacao");
      bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
      return ResponseEntity.badRequest().body(response);
    }

    Optional<Sistema> optionalSistema = sistemaService.encontraSistemaPorToken(token);
    if (optionalSistema.isEmpty()) {
      erros.add("Empresa nao encontrada");
    } else {
      Sistema sistema = optionalSistema.get();

      if (usuarioService.buscaPorCodigo(dto.getUsuario()).isPresent()) {
        Gastos g = Gastos.builder().valor(BigDecimal.valueOf(dto.getValor())).
            categoria(0).codigoUsuario(dto.getUsuario()).data(dto.getData()).descricao(dto.getDescricao()).sistema(sistema.getId()).
            build();

        Optional<Categoria> optionalCategoria = gastosService.encontraCategoriaPorGastoDoUsuario(dto.getUsuario(), dto.getDescricao());
        Categoria cat = optionalCategoria.orElse(null);
        g.setCategoria(cat != null ? cat.getId() : 0);
        Gastos gastos = gastosService.insereGasto(g);

        RetornoGastoDTO retDto = RetornoGastoDTO.builder()
            .categoria(gastos.getCategoria())
            .valor(gastos.getValor())
            .codigoUsuario(gastos.getCodigoUsuario())
            .data(gastos.getData())
            .descricao(gastos.getDescricao())
            .id(gastos.getId())
            .sistema(gastos.getSistema())
            .descricaoCategoria(cat != null ? cat.getNome() : "")
            .build();

        response.setData(retDto);
      } else {
        erros.add("Usuario não encontrado");
      }

    }
    response.setErrors(erros);
    return ResponseEntity.ok(response);
  }

  @PutMapping(value = "atualiza")
  public ResponseEntity atualizaCategoria(@RequestBody @Valid AtualizaCategoriaDTO dto, BindingResult bindingResult) {
    log.info("atualizaCategoria");
    Response response = new Response();
    List<String> erros = new ArrayList<>();

    if (bindingResult != null && bindingResult.hasErrors()) {
      log.warn("incluirGasto - erros de validacao");
      bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
      return ResponseEntity.badRequest().body(response);
    }

    boolean salvarCategoria = true;
    if (dto.getIdCategoria() != null && dto.getIdCategoria() > 0) {
      if (categoriaService.encontraCategoria(dto.getIdCategoria()).isPresent()) {
        salvarCategoria = false;
      }
    }

    if (salvarCategoria) {
      Categoria salvar = categoriaService.salvar(Categoria.builder().nome(dto.getDescricao()).build());
      dto.setIdCategoria(salvar.getId());
    }

    Gastos gastos = gastosService.atualizaCategoria(dto.getIdCategoria(), dto.getGastoId());
    response.setData(convertToDto(gastos));
    response.setErrors(erros);
    return ResponseEntity.ok(response);
  }


  @GetMapping(value = "filtrar")
  public ResponseEntity listarPorFuncionarioId(@RequestBody @Valid FiltraGastosDTO dto, BindingResult bindingResult) {
    log.info("Buscando gastos");
    Response response = new Response();

    if (bindingResult != null && bindingResult.hasErrors()) {
      log.warn("incluirGasto - erros de validacao");
      bindingResult.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
      return ResponseEntity.badRequest().body(response);
    }

    LocalDateTime dataInicial = dto.getDataInicial();
    LocalDateTime dataFinal = dto.getDataFinal();
    if (dataInicial == null && dataFinal == null) {
      dataFinal = LocalDateTime.now().atZone(ZoneId.of("UTC")).toLocalDateTime();
      dataInicial = dataFinal.minusSeconds(5);
    }

    int pagina = dto.getPage() > 0 ? dto.getPage() : 0;
    int qtd = dto.getQtdPorPagina() > 0 ? dto.getQtdPorPagina() : qtdPorPagina;

    Page<Gastos> gastos = gastosService.retornaGastoPorUsuario(dto.getUsuario(), dataInicial, dataFinal, pagina, qtd);

    Page<RetornoGastoDTO> map = gastos.map(g -> convertToDto(g));

    response.setData(map);
    return ResponseEntity.ok(response);
  }

  private RetornoGastoDTO convertToDto(Gastos g) {
    Categoria categoria = categoriaService.encontraCategoria(g.getCategoria()).orElse(null);
    return RetornoGastoDTO.builder()
        .descricaoCategoria(categoria != null ? categoria.getNome() : "")
        .descricao(g.getDescricao())
        .sistema(g.getSistema())
        .id(g.getId())
        .data(g.getData())
        .codigoUsuario(g.getCodigoUsuario())
        .categoria(categoria != null ? categoria.getId() : 0)
        .valor(g.getValor())
        .build();
  }
}
