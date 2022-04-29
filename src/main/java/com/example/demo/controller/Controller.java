package com.example.demo.controller;

import java.util.List;

import com.example.demo.entity.Compra;
import com.example.demo.service.ClienteServiceImpl;
import com.example.demo.service.CompraServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class Controller {
  private final ClienteServiceImpl clientService;
  private final CompraServiceImpl compraService;

  @Autowired
  public Controller(ClienteServiceImpl clientService, CompraServiceImpl compraService) {
    this.clientService = clientService;
    this.compraService = compraService;
  }

  @GetMapping("/compras")
  public List<Compra> compras() {
    return this.compraService.getListaCompras();
  }

  @GetMapping("/maior-compra/{ano}")
  public Compra compras(@PathVariable int ano) {
    return this.compraService.getMaiorCompraDoAno(ano);
  }
}
