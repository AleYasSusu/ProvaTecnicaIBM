package com.example.demo.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class Compra implements Serializable, Comparable<Compra>{
  private String codigo;
  private String data;
  private String cliente;
  private Produto[] itens;
  private Double valorTotal;

  @Override
  public int compareTo(Compra o){
    if(this.getValorTotal() == o.getValorTotal())
      return 0;
    return this.getValorTotal() < o.getValorTotal() ? -1 : 1;
  }
}
