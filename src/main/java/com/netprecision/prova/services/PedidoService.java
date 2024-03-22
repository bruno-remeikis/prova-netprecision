package com.netprecision.prova.services;

import com.netprecision.prova.models.Pedido;
import com.netprecision.prova.models.Produto;
import com.netprecision.prova.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PedidoService
{
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    public Integer create() {
        return pedidoRepository.save(new Pedido()).getId();
    }

    public Pedido findById(int id) {
        return pedidoRepository.findById(id).orElse(null);
    }
    public Pedido addProdutos(int id, List<Integer> idProdutos) throws Exception {
        Pedido p = pedidoRepository.findById(id).orElse(null);

        if(p == null)
            throw new Exception("Produto não encontrado.");

        List<Produto> produtos = produtoService.findAllById(idProdutos);

        if(produtos.stream().anyMatch(Objects::isNull))
            throw new Exception("Um ou mais produtos informados não existe.");

        p.getProdutos().addAll(produtos);
        return pedidoRepository.save(p);
    }
}
