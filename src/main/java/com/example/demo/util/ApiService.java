package com.example.demo.util;

import java.util.ArrayList;
import java.util.Arrays;

import com.example.demo.entity.Cliente;
import com.example.demo.entity.Compra;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ApiService {

  private final RestTemplate restTemplate;

  public ApiService(RestTemplateBuilder restTemplateBuilder) {
      this.restTemplate = restTemplateBuilder.build();
  }
  
  public ArrayList<Cliente> getClientes() {
    String url = "http://www.mocky.io/v2/598b16291100004705515ec5";
    Cliente[] clientes =  this.restTemplate.getForObject(url, Cliente[].class);
    return new ArrayList<Cliente>(Arrays.asList(clientes));
  }

  public ArrayList<Compra> getCompras() {
    String url = "http://www.mocky.io/v2/598b16861100004905515ec7";
    Compra[] compras = this.restTemplate.getForObject(url, Compra[].class);
    return new ArrayList<Compra>(Arrays.asList(compras));
  }
}
