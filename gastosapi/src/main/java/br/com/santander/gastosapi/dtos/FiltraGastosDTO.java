package br.com.santander.gastosapi.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class FiltraGastosDTO implements Serializable {

  @NotNull(message = "Usuário não informado")
  @Min(value = 1, message = "Usuário inválido")
  private int usuario;

  private int page;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'")
  private LocalDateTime dataInicial;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss'Z'")
  private LocalDateTime dataFinal;

  private int qtdPorPagina;

  public static void main(String[] args) {
    System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss'Z'")));
  }

}
