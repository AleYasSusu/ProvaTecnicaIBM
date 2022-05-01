package com.example.demo.service;

import com.example.demo.entity.Compra;
import com.example.demo.entity.Produto;
import com.example.demo.util.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProdutoServiceImpl {
    @SuppressWarnings("unused")
	private final ApiService api;
    private final CompraServiceImpl compraService;

    @Autowired
    public ProdutoServiceImpl(ApiService api, CompraServiceImpl compraService) {
        this.api = api;
        this.compraService = compraService;
    }

    public List<Produto> findRecommendation(String cpf) {
        List<Compra> compras = this.compraService.findAllPurchaseByCpfClient(cpf);
        Map<String, Integer> categoriasMaisCompradas = new HashMap<>();
        Map<String, List<Produto>> categoriaComProduto = new HashMap<>();

        compras.forEach(compra -> compra.getItens().forEach(item -> {
            String variedade = item.getVariedade();
            String categoria = item.getCategoria();

            Integer valorAtual = categoriasMaisCompradas.get(categoria);
            valorAtual = valorAtual != null ? valorAtual : 0;
            categoriasMaisCompradas.put(categoria, ++valorAtual);

            List<Produto> produtos = categoriaComProduto.get(categoria);
            if (produtos == null) {
                produtos = new ArrayList<>();
            }
            boolean existeProduto = isExisteProduto(variedade, produtos);
            if (!existeProduto) {
                produtos.add(item);
            }
            categoriaComProduto.put(item.getCategoria(), produtos);
        }));

        Map.Entry<String,Integer> sorted = categoriasMaisCompradas.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);

        String categoriaMaisComprada = sorted != null ? sorted.getKey() : "";

        return categoriaComProduto.get(categoriaMaisComprada);
    }

    private boolean isExisteProduto(String variedade, List<Produto> produtos) {
        return produtos.stream()
                .filter(produto -> produto.getVariedade().equals(variedade))
                .findFirst()
                .orElse(null) != null;
    }
}
