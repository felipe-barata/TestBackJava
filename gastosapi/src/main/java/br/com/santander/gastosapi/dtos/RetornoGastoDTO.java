package br.com.santander.gastosapi.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class RetornoGastoDTO implements Serializable {

  private Long id;
  private String descricao;
  private BigDecimal valor;
  private Integer codigoUsuario;
  private LocalDateTime data;
  private Integer categoria;
  private String descricaoCategoria;
  private Integer sistema;

}
