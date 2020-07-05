package br.com.santander.gastosapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO para retornar o Sistema cadastrado na base com seu ID e Token
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SistemaDTO implements Serializable {

  private int codigoSistema;
  private int idSistema;
  private String token;

}
