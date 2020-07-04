package br.com.santander.gastosapi.repository;

import br.com.santander.gastosapi.domain.Sistema;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SistemaRepository extends JpaRepository<Sistema, Integer> {
}
