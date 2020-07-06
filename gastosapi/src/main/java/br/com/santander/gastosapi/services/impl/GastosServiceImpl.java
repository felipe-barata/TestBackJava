package br.com.santander.gastosapi.services.impl;

import br.com.santander.gastosapi.domain.Categoria;
import br.com.santander.gastosapi.domain.Gastos;
import br.com.santander.gastosapi.repository.CategoriaRepository;
import br.com.santander.gastosapi.repository.GastosRepository;
import br.com.santander.gastosapi.services.GastosService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Slf4j
public class GastosServiceImpl implements GastosService {

  @Autowired
  private GastosRepository gastosRepository;

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Override
  public Gastos insereGasto(Gastos gastos) {
    log.info("insereGasto");
    Gastos save = gastosRepository.save(gastos);
    if (save != null) {
      log.info("insereGasto - inseriu gasto com id: {}", save.getId());
    }
    return save;
  }

  @Override
  public Page<Gastos> retornaGastoPorUsuario(Integer usuario, LocalDateTime dataInicial, LocalDateTime dataFinal, Integer page, Integer size) {
    log.info("retornaGastoPorUsuario - usuario: {}, dataInicial: {}, dataFinal: {}, page: {}, size: {}",
        usuario, dataInicial, dataFinal, page, size);
    PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.DESC, "data");
    return gastosRepository.retornaGastosPorUsuarioPaginado(usuario, dataInicial, dataFinal, pageRequest);
  }

  @Override
  public Gastos atualizaCategoria(Integer categoria, Long gastoId) {
    log.info("atualizaCategoria - categoria: {}, gastoId: {}", categoria, gastoId);
    gastosRepository.updateCategoriaGasto(categoria, gastoId);
    return gastosRepository.findById(gastoId).orElse(null);
  }

  @Override
  public Optional<Categoria> encontraCategoriaPorGastoDoUsuario(Integer usuario, String descricao) {
    return categoriaRepository.encontraCategoriaPorGastoDoUsuario(usuario, descricao);
  }

}
