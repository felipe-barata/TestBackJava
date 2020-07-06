package br.com.santander.gastosapi.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
public class CadastraUsuarioDTO implements Serializable {

  @NotBlank(message = "O nome deve ser informado")
  private String nome;

  @NotBlank(message = "A senha deve ser informada")
  @Size(min = 6, message = "A Senha deve ter pelo menos 6 caracteres")
  private String senha;

  @NotBlank(message = "O email deve ser informado")
  @Email(message = "Email inválido")
  private String email;

}
