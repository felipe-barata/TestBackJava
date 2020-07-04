package br.com.santander.gastosapi.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO para retornar os gastos do usuário
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastosUsuarioDTO extends GastosDTO{

  private String categoria;

}
