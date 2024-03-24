package com.netprecision.prova.services;

import com.netprecision.prova.exceptions.BusinessException;
import com.netprecision.prova.models.ItemPedido;
import com.netprecision.prova.models.Pedido;
import com.netprecision.prova.models.Produto;
import com.netprecision.prova.models.dto.ItemPedidoDTO;
import com.netprecision.prova.repositories.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PedidoService
{
    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoService itemPedidoService;

    public Pedido findByIdMandatory(int id, boolean apenasAberto) throws BusinessException {
        Pedido p = pedidoRepository.findById(id).orElse(null);

        if(p == null)
            throw new BusinessException("Não existe pedido aberto com ID " + id + ".", HttpStatus.NOT_FOUND);

        if(apenasAberto && p.isFechado())
            throw new BusinessException("Este pedido já está fechado.", HttpStatus.LOCKED);

        return p;
    }

    public Pedido create() {
        return pedidoRepository.save(new Pedido());
    }

    public Pedido findById(int id) {
        return pedidoRepository.findById(id).orElse(null);
    }

    public float calcularTotal(int id) throws BusinessException {
        float total =  pedidoRepository.calcularTotal(id);

        if(total == -1)
            throw new BusinessException("Não existe pedido aberto com ID " + id + ".", HttpStatus.NOT_FOUND);

        return total;
    }

    public float calcularTotal(int id, List<ItemPedidoDTO> dtos) throws BusinessException {
        Pedido p = findByIdMandatory(id, true);

        List<ItemPedido> itensNovos = new LinkedList();

        for(ItemPedidoDTO dto: dtos) {
            boolean itemNovo = true;
            for(ItemPedido i: p.getItensPedidos()) {
                if(i.getProduto().getCodigo().equals(dto.getCodigoProduto())) {
                    i.setQuantidade(i.getQuantidade() + dto.getQuantidade());
                    itemNovo = false;
                    break;
                }
            }

            if(itemNovo) {
                Produto produto = produtoService.findById(dto.getCodigoProduto());
                ItemPedido itemPedido = itemPedidoService.save(
                    new ItemPedido(null, produto, dto.getQuantidade())
                );
                itensNovos.add(itemPedido);
            }
        }

        p.getItensPedidos().addAll(itensNovos);
        pedidoRepository.save(p);
        return pedidoRepository.calcularTotal(id);
    }

    public Pedido addItemAoPedido(int id, ItemPedidoDTO dto) throws BusinessException
    {
        Pedido p = findByIdMandatory(id, true);

        // Busca o item do pedido com o código de produto especificado em `dto`
        ItemPedido itemPedido = p.getItemByCodigoProduto(dto.getCodigoProduto());

        // Caso o produto ainda não tenha sido solicitado, adiciona-o ao pedido
        if(itemPedido == null) {
            Produto produto = produtoService.findById(dto.getCodigoProduto());

            if(produto == null)
                throw new BusinessException("Produto de código " + dto.getCodigoProduto() + " não encontrado.", HttpStatus.NOT_FOUND);

            itemPedido = itemPedidoService.save(
                new ItemPedido(null, produto, dto.getQuantidade())
            );
            p.getItensPedidos().add(itemPedido);
        }
        // Caso o produto já tenha sido pedido anteriormente, apenas adiciona à sua `quantidade`
        else
            itemPedido.setQuantidade(itemPedido.getQuantidade() + dto.getQuantidade());

        return pedidoRepository.save(p);
    }

    public Pedido fechar(int id) throws BusinessException {
        Pedido p = findByIdMandatory(id, true);

        p.setFechado(true);
        return pedidoRepository.save(p);
    }

    public Pedido removerItemDoPedido(int id, ItemPedidoDTO dto) throws BusinessException
    {
        Pedido p = findByIdMandatory(id, true);

        // Busca o item do pedido com o código de produto especificado em `dto`
        ItemPedido itemPedido = p.getItemByCodigoProduto(dto.getCodigoProduto());

        // Caso o produto ainda não tenha sido solicitado:
        if(itemPedido == null) {
            throw new BusinessException("O produto de código " + dto.getCodigoProduto() + " não foi solicitado neste pedido.", HttpStatus.LOCKED);
        }
        // Caso o produto já tenha sido pedido anteriormente, apenas adiciona à sua `quantidade`
        else {
            if(dto.getQuantidade() > itemPedido.getQuantidade())
                throw new BusinessException("A quantidade de itens que deseja remover é maior que a quantidade total.", HttpStatus.LOCKED);

            if(dto.getQuantidade() == itemPedido.getQuantidade())
                itemPedidoService.delete(itemPedido);
            else
                itemPedido.setQuantidade(itemPedido.getQuantidade() - dto.getQuantidade());
        }

        return pedidoRepository.save(p);
    }
}
