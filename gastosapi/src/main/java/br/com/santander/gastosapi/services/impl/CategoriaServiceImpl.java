package br.com.santander.gastosapi.services.impl;

import br.com.santander.gastosapi.domain.Categoria;
import br.com.santander.gastosapi.repository.CategoriaRepository;
import br.com.santander.gastosapi.services.CategoriaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class CategoriaServiceImpl implements CategoriaService {

  @Autowired
  private CategoriaRepository categoriaRepository;

  @Override
  public Page<Categoria> sugerirCategoria(String descricao, Integer page, Integer size) {
    log.debug("sugerirCategoria - descricao: {}, page: {}, size: {}", descricao, page, size);
    PageRequest pageRequest = PageRequest.of(page, size, Sort.Direction.ASC, "nome");
    return categoriaRepository.retornaCategoriaPorDescricao(descricao.toLowerCase(), pageRequest);
  }

  @Override
  public Optional<Categoria> encontraCategoria(Integer categoria) {
    log.info("encontraCategoria - categoria: {}", categoria);
    return categoriaRepository.findById(categoria);
  }

  @Override
  public Categoria salvar(Categoria categoria) {
    log.info("salvar");
    return categoriaRepository.save(categoria);
  }
}
