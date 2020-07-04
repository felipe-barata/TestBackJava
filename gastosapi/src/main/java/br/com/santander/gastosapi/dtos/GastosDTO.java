package br.com.santander.gastosapi.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO para inserir gastos por usuario
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastosDTO implements Serializable {

  //{ "descricao": "alfanumerico", "valor": double americano, "codigousuario": numerico, "data": Data dem formato UTC }

  private String descricao;
  private double valor;
  @JsonProperty(value = "codigousuario")
  private int usuario;
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'")
  private LocalDateTime data;
}
