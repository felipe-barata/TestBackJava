package br.com.santander.gastosapi.dtos;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
public class AtualizaCategoriaDTO implements Serializable {

  @NotNull(message = "ID do gasto deve ser informado")
  @Min(value = 1, message = "ID do gasto inválido")
  private Long gastoId;

  @NotBlank(message = "Descrição não iformada")
  private String descricao;

  private Integer idCategoria;
  
}
