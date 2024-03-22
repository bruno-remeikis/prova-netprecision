package com.netprecision.prova.models;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

public class ProdutoPedido {
    @Id
    private Integer id;

    @OneToOne
    private Produto produto;

    @Column
    private Integer quantidade;
}
