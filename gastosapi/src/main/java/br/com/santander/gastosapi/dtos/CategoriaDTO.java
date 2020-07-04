package br.com.santander.gastosapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para retornar sugestões de categoria
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoriaDTO extends CadastraCategoriaDTO {

  private int codigoCategoria;

}
