package br.com.santander.gastosapi.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
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

  @NotBlank(message = "Descrição não pode ser vazia")
  private String descricao;
  
  @DecimalMin(value = "0.01", message = "Valor inválido")
  private double valor;

  @NotNull(message = "Usuario não informado")
  @JsonProperty(value = "codigousuario")
  private int usuario;

  @NotNull(message = "Data não informada")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'")
  private LocalDateTime data;
}
