package br.com.santander.gastosapi.services;

import br.com.santander.gastosapi.domain.Gastos;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

public interface GastosService {

  Gastos insereGasto(Gastos gastos);

  Page<Gastos> retornaGastoPorUsuario(Integer usuario, LocalDateTime dataInicial, LocalDateTime dataFinal, Integer page, Integer size);

  Gastos atualizaCategoria(Integer categoria, Long gastoId);

}
