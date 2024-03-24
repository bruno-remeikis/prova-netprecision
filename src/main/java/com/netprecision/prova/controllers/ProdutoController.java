package com.netprecision.prova.controllers;

import com.netprecision.prova.services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "https://prova-netprecision-web.vercel.app/")
public class ProdutoController
{
    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity findAll() {
        return ResponseEntity.ok(
            produtoService.findAll()
        );
    }
}
