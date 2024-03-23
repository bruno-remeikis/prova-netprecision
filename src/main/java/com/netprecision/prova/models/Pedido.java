package com.netprecision.prova.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_pedido")
    private List<ItemPedido> itensPedidos;

    @Column(nullable = false)
    private boolean fechado = false;
}
