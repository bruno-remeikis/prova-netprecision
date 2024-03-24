package com.netprecision.prova.controllers;

import com.netprecision.prova.models.Pedido;
import com.netprecision.prova.models.dto.ItemPedidoDTO;
import com.netprecision.prova.services.PedidoService;
//import io.swagger.v3.oas.annotations.Operation;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
//@Slf4j
@RequestMapping("/pedido")
@CrossOrigin(origins = "https://prova-netprecision-web.vercel.app/")
public class PedidoController
{
    @Autowired
    private PedidoService pedidoService;

    @GetMapping("/{id}")
    //@Operation(summary = "TESTE", method = "GET")
    public ResponseEntity findById(@PathVariable int id) {
        Pedido p = pedidoService.findById(id);

        return p != null
            ? ResponseEntity.ok(p)
            : ResponseEntity.status(HttpStatus.NOT_FOUND).body("Código do pedido inválido.");
    }

    @GetMapping("/{id}/total")
    public ResponseEntity calcularTotal(@PathVariable int id) {
        try {
            return ResponseEntity.ok(
                pedidoService.calcularTotal(id)
            );
        }
        catch(Exception e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
        }
    }

    @PostMapping
    public ResponseEntity criarPedido() {
        return ResponseEntity.ok(
            pedidoService.create()
        );
    }

    @PostMapping("/{id}")
    public ResponseEntity calcularTotal(@PathVariable int id, @RequestBody List<ItemPedidoDTO> dtos) {
        try {
            return ResponseEntity.ok(
                pedidoService.calcularTotal(id, dtos)
            );
        }
        catch(Exception e) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
        }
    }

    @PostMapping("/{id}/adicionar")
    public ResponseEntity addItemAoPedido(@PathVariable int id, @RequestBody ItemPedidoDTO dto) {
        try {
            return ResponseEntity.ok(
                pedidoService.addItemAoPedido(id, dto)
            );
        }
        catch(Exception e) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
        }
    }

    @PutMapping("/{id}/fechar")
    public ResponseEntity fechar(@PathVariable int id) {
        try {
            return ResponseEntity.ok(
                pedidoService.fechar(id)
            );
        }
        catch(Exception e) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}/remover")
    public ResponseEntity removerItemDoPedido(@PathVariable int id, @RequestBody ItemPedidoDTO dto) {
        try {
            return ResponseEntity.ok(
                pedidoService.removerItemDoPedido(id, dto)
            );
        }
        catch(Exception e) {
            return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(e.getMessage());
        }
    }
}
