package br.com.santander.gastosapi.dtos;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UsuarioDTO implements Serializable {

  private int id;
  private String nome;
  private String email;
}
