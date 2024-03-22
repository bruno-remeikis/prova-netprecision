package com.netprecision.prova.controllers;

import com.netprecision.prova.models.Pedido;
import com.netprecision.prova.models.Produto;
import com.netprecision.prova.services.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedido")
public class PedidoController
{
    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public ResponseEntity criarPedido() {
        return ResponseEntity.ok(
            pedidoService.create()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity get(@PathVariable int id) {
        Pedido p = pedidoService.findById(id);

        return p != null
            ? ResponseEntity.ok(p)
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Código do pedido inválido.");
    }

    @PostMapping("/{id}")
    public ResponseEntity addProdutos(@PathVariable int id, @RequestBody List<Integer> Idprodutos) {
        try {
            return ResponseEntity.ok(
                pedidoService.addProdutos(id, Idprodutos)
            );
        }
        catch(Exception e) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
        }
    }
}
