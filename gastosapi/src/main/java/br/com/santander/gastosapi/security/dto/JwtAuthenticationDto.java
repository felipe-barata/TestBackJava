package br.com.santander.gastosapi.security.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtAuthenticationDto {

  @NotEmpty(message = "Email não pode ser vazio")
  @Email(message = "Email inválido")
  private String email;
  @NotEmpty(message = "Senha não pode ser vazia")
  private String senha;

  @Override
  public String toString() {
    return "JwtAuthenticationRequestDto [email=" + email + ", senha=" + senha + "]";
  }

}
