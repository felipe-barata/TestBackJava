package br.com.santander.gastosapi.repository;

import br.com.santander.gastosapi.domain.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SistemaRepository extends JpaRepository<Sistema, Integer> {

  Optional<Sistema> findByToken(String token);

  Optional<Sistema> findByCodigoSistema(Integer codigoSistema);
}
