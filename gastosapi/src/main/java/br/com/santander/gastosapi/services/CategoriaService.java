package br.com.santander.gastosapi.services;

import br.com.santander.gastosapi.domain.Categoria;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CategoriaService {

  Page<Categoria> sugerirCategoria(String descricao, Integer page, Integer size);

  Optional<Categoria> encontraCategoria(Integer categoria);

  Categoria salvar(Categoria categoria);
}
