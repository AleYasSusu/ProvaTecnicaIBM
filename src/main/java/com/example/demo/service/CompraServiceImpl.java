package com.example.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entity.Compra;
import com.example.demo.util.ApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompraServiceImpl {
  private final ApiService api;

  @Autowired
  public CompraServiceImpl(ApiService api) {
    this.api = api;
  }

  public List<Compra> getListaCompras() {
    ArrayList<Compra> compras = api.getCompras();
    Collections.sort(compras);
    return compras;
  }

  public Compra getMaiorCompraDoAno(int ano) {
    ArrayList<Compra> compras = (ArrayList<Compra>) getListaCompras();
    ArrayList<Compra> comprasDoAno = new ArrayList<Compra>();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    for (Compra c : compras) {
      LocalDate localDate = LocalDate.parse(c.getData(), formatter);
      if (localDate.getYear() == ano) {
        comprasDoAno.add(c);
      }
    }
    Collections.sort(comprasDoAno);
    return comprasDoAno.get(comprasDoAno.size() - 1);
  }

  public List<Compra> findAllPurchaseByCpfClient(String cpf) {
    List<Compra> todasCompras = this.getListaCompras();
    return todasCompras.stream()
            .filter(compra -> compra.getCliente().substring(1).equals(cpf.replaceAll("-", ".")))
            .collect(Collectors.toList());
  }
}
