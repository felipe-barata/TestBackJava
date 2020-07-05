package br.com.santander.gastosapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  DTO para incluir um novo sistema na base de dados com base em uma Chave para o token
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastroSistemaDTO implements Serializable {

  private int codigoSistema;
  private String nome;
  private String chaveToken;

}
