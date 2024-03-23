package com.netprecision.prova.services;

import com.netprecision.prova.models.ItemPedido;
import com.netprecision.prova.repositories.ItemPedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemPedidoService
{
    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    public ItemPedido save(ItemPedido itemPedido) {
        return itemPedidoRepository.save(itemPedido);
    }

    public void delete(ItemPedido itemPedido) {
        itemPedidoRepository.delete(itemPedido);
    }
}
