package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.entity.Cliente;
import com.example.demo.entity.Compra;
import com.example.demo.util.ApiService;

@Service
public class ClienteServiceImpl {
  private final ApiService api;
  private final CompraServiceImpl comprasService;

  @Autowired
  public ClienteServiceImpl(ApiService api, CompraServiceImpl comprasService) {
    this.api = api;
    this.comprasService = comprasService;
  }

  public List<Cliente> findAll() {
    return api.getClientes();
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

  public List<ClienteDTO> findTopThreeClients() {
    List<Cliente> clientes = this.findAll();
    List<ClienteDTO> listaClientes= new ArrayList<>();

    clientes.forEach(cliente -> {
      List<Compra> comprasCliente = this.comprasService.findAllPurchaseByCpfClient(cliente.getCpf());
      int quantidade = comprasCliente.size();
      double valorTotalCompras = comprasCliente.stream().mapToDouble(Compra::getValorTotal).sum();
      ClienteDTO clienteDTO = ClienteDTO.builder()
              .nome(cliente.getNome())
              .cpf(cliente.getCpf())
              .quantidadeCompras(quantidade)
              .valorTotalCompras(valorTotalCompras)
              .build();

      listaClientes.add(clienteDTO);
    });

    List<ClienteDTO> retorno = listaClientes.stream()
            .sorted((c1, c2) -> {
              int cmp = Integer.compare(c2.getQuantidadeCompras(), c1.getQuantidadeCompras());
              if (cmp == 0) {
                return Double.compare(c1.getValorTotalCompras(), c2.getValorTotalCompras());
              }
              return cmp;
            })
            .collect(Collectors.toList());

    return retorno.subList(0, 3);
  }

}
