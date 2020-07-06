package br.com.santander.gastosapi.dtos;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class SugestaoCategoriaDTO implements Serializable {

  @NotBlank(message = "Descrição não informada")
  @Size(min = 3, message = "Informe pelo menos três caracteres")
  private String descricao;
  private int pagina;
  private int qtdDeRegistros;

}
