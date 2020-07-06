package br.com.santander.gastosapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 *  DTO para incluir um novo sistema na base de dados com base em uma Chave para o token
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastroSistemaDTO implements Serializable {

  @NotNull(message = "Codigo do sistema deve ser informado")
  @Min(value = 1, message = "Codigo do sistema não informado")
  private int codigoSistema;

  @NotBlank(message = "Nome do sistema deve ser informado")
  private String nome;

  @NotBlank(message = "Chave do token deve ser informada")
  private String chaveToken;

}
