package com.netprecision.prova.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "ItemPedito_id_seq", sequenceName = "ItemPedido_id_seq")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "codigo_produto")
    private Produto produto;

    @Column(nullable = false)
    private Integer quantidade;

    /*@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;*/

    /*public ItemPedido(Integer id, Produto produto, Integer quantidade) {
        this.id = id;
        this.produto = produto;
        this.quantidade = quantidade;
    }*/
}
