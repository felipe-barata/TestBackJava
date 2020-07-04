package br.com.santander.gastosapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 *  DTO para incluir uma nova categoria na base de dados
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CadastraCategoriaDTO implements Serializable {

  private String descricao;

}
