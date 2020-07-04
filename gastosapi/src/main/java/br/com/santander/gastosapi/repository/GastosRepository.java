package br.com.santander.gastosapi.repository;

import br.com.santander.gastosapi.domain.Gastos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GastosRepository extends JpaRepository<Gastos, Long> {
}
