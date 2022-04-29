package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Compra;
import com.example.demo.util.ApiService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteServiceImpl {
  private final ApiService api;

  @Autowired
  public ClienteServiceImpl(ApiService api) {
    this.api = api;
  }

  public Cliente findByCpf(String cpf) {
    ArrayList<Cliente> clientes = api.getClientes();
    for (Cliente x : clientes) {
      if (x.getCpf().equals(cpf)) {
        return x;
      }
    }
    return null;
  }

}
