package br.com.santander.gastosapi.repository;

import br.com.santander.gastosapi.domain.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

  @Query(value = "select * from categoria where LOWER(nome) like %:descricao%", nativeQuery = true)
  Page<Categoria> retornaCategoriaPorDescricao(@Param("descricao") String descricao, Pageable pageable);

  @Query(value = "select c.* from gastos g inner join categoria c on (g.categoria = c.id) where g.codigousuario = :usuario and g.descricao = :descricao group by g.categoria LIMIT 1", nativeQuery = true)
  Optional<Categoria> encontraCategoriaPorGastoDoUsuario(@Param("usuario") Integer usuario, @Param("descricao") String descricao);

}
