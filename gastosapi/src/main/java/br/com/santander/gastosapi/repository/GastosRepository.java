package br.com.santander.gastosapi.repository;

import br.com.santander.gastosapi.domain.Gastos;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDateTime;

@Repository
public interface GastosRepository extends JpaRepository<Gastos, Long> {

  Page<Gastos> findByCodigoUsuario(@Param("codigoUsuario") Integer codigoUsuario, Pageable pageable);

  @Modifying
  @Transactional
  @Query(value = "update gastos set categoria = :categoria where id = :id", nativeQuery = true)
  void updateCategoriaGasto(@Param("categoria") Integer categoria, @Param("id") Long id);

  @Query(value = "select * from gastos where data between :dataInicial and :dataFinal and codigousuario = :usuario", nativeQuery = true)
  Page<Gastos> retornaGastosPorUsuarioPaginado(@Param("usuario") Integer usuario, @Param("dataInicial") LocalDateTime dataInicial, @Param("dataFinal")
      LocalDateTime dataFinal, Pageable pageable);

}
