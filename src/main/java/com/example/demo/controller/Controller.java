package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.ClienteDTO;
import com.example.demo.entity.Compra;
import com.example.demo.entity.Produto;
import com.example.demo.service.ClienteServiceImpl;
import com.example.demo.service.CompraServiceImpl;
import com.example.demo.service.ProdutoServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/")
public class Controller {
	private final ClienteServiceImpl clientService;
	private final CompraServiceImpl compraService;
	private final ProdutoServiceImpl produtoService;

	@Autowired
	public Controller(ClienteServiceImpl clientService, CompraServiceImpl compraService,
			ProdutoServiceImpl produtoService) {
		this.clientService = clientService;
		this.compraService = compraService;
		this.produtoService = produtoService;
	}

	@ApiOperation(value = "Retorna uma lista de compras em ordem crescente")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Retorna a lista de compra em ordem crescente"),
	@ApiResponse(code = 500, message = "Foi gerada uma exceção"), })
	@GetMapping("/compras")
	public List<Compra> compras() {
		return this.compraService.getListaCompras();
	}

	@ApiOperation(value = "Retornar a maior compra do ano com dados da compra")
	@GetMapping("/maior-compra/{ano}")
	public Compra compras(@PathVariable int ano) {
		return this.compraService.getMaiorCompraDoAno(ano);
	}

	@ApiOperation(value = "Retornar o Top 3 clientes mais fiéis, clientes que possuem mais compras recorrentes com maiores valores")
	@GetMapping("/findTopThreeClients")
	public List<ClienteDTO> findTopThreeClients() {
		return this.clientService.findTopThreeClients();
	}

	@ApiOperation(value = "Retornar uma lista de recomendações de vinhos baseado nos tipos de vinho que o cliente mais compra")
	@GetMapping("/findRecommendation/{cpf}")
	public List<Produto> findRecommendation(@PathVariable String cpf) {
		return this.produtoService.findRecommendation(cpf);
	}
}
