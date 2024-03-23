package com.netprecision.prova.repositories;

import com.netprecision.prova.models.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PedidoRepository extends JpaRepository<Pedido, Integer>
{
    @Query(/*"""
        select sum((i.quantidade * p2.preco))
        from Pedido p1
        inner join ItemPedido i
            on p1.id = i.id_pedido
        inner join Produto p2
            on i.codigo_produto = p2.codigo
        where p1.id = ?1
    """*/
    """
    select COALESCE(sum(i.quantidade * p2.preco), -1)
    from Pedido p
    inner join p.itensPedidos i
    inner join i.produto p2
    where p.id = ?1
    """)
    float calcularTotal(int id);
}
