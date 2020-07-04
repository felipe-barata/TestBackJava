package br.com.santander.gastosapi.repository;

import br.com.santander.gastosapi.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
}
