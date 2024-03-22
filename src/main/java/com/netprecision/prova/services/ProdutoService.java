package com.netprecision.prova.services;

import com.netprecision.prova.models.Produto;
import com.netprecision.prova.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class ProdutoService implements CommandLineRunner
{
    @Autowired
    private ProdutoRepository produtoRepository;

    @Override
    public void run(String... args) throws Exception {
        produtoRepository.saveAll(Arrays.asList(
            new Produto(1147, "Cachorro Quente", 3),
            new Produto(1154, "Bauru", 2.5f),
            new Produto(1164, "Misto Quente", 2),
            new Produto(1155, "X-Burguer", 6)
        ));
    }

    public List<Produto> findAll() {
        return produtoRepository.findAll();
    }

    public List<Produto> findAllById(List<Integer> ids) {
        return produtoRepository.findAllById(ids);
    }
}
