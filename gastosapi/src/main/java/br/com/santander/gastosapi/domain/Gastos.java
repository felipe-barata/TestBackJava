package br.com.santander.gastosapi.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "gastos")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Gastos implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;
  private String descricao;
  @Column(columnDefinition = "double")
  private BigDecimal valor;
  @Column(name = "codigousuario")
  private Integer codigoUsuario;
  private LocalDateTime data;
  private Integer categoria;
  @Column(name = "codigosistema")
  private Integer sistema;

}
