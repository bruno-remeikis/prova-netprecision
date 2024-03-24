package com.netprecision.prova.services;

import com.netprecision.prova.models.ItemPedido;
import com.netprecision.prova.models.Pedido;
import com.netprecision.prova.models.Produto;
import com.netprecision.prova.models.dto.ItemPedidoDTO;
import com.netprecision.prova.repositories.PedidoRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PedidoServiceTest {

    @InjectMocks
    PedidoService pedidoService;

    @Mock
    PedidoRepository pedidoRepository;

    @Mock
    private ProdutoService produtoService;

    @Mock
    private ItemPedidoService itemPedidoService;

    @Nested
    class FindByIdMandatory
    {
        /*private Method getFindByIdMethod() throws NoSuchMethodException {
            Method method = PedidoService.class.getDeclaredMethod("findByIdMandatory");
            method.setAccessible(true);
            return method;
        }*/

        @Test
        @DisplayName("Deve retornar um Pedido com sucesso")
        void deveRetornarUmPedidoComSucesso() throws Exception {
            Pedido p = new Pedido(1, List.of(), false);

            doReturn(Optional.of(p))
                .when(pedidoRepository).findById(p.getId());

            var output = pedidoService.findByIdMandatory(p.getId(), false);

            assertNotNull(output);
            assertEquals(p, output);
        }

        @Test
        @DisplayName("Deve retornar um Pedido aberto com sucesso")
        void deveRetornarUmPedidoAbertoComSucesso() throws Exception {
            Pedido p = new Pedido(1, List.of(), false);

            doReturn(Optional.of(p))
                    .when(pedidoRepository).findById(p.getId());

            var output = pedidoService.findByIdMandatory(p.getId(), true);

            assertNotNull(output);
            assertEquals(p, output);
        }

        @Test
        @DisplayName("Deve retornar um Pedido fechado com sucesso")
        void deveRetornarUmPedidoFechadoComSucesso() throws Exception {
            Pedido p = new Pedido(1, List.of(), true);

            doReturn(Optional.of(p))
                .when(pedidoRepository).findById(p.getId());

            var output = pedidoService.findByIdMandatory(p.getId(), false);

            assertNotNull(output);
            assertEquals(p, output);
        }

        @Test
        @DisplayName("Deve lançar Exception quando Pedido estiver fechado")
        void deveLancarExceptionQuandoPedidoEstiverFechado() throws Exception {
            Pedido p = new Pedido(1, List.of(), true);

            doReturn(Optional.of(p))
                .when(pedidoRepository).findById(p.getId());

            assertThrows(
                Exception.class,
                () -> pedidoService.findByIdMandatory(p.getId(), true)
            );
        }

        @Test
        @DisplayName("Deve lançar Exception quando Pedido não existir")
        void deveLancarExceptionQuandoPedidoNaoExistir() {
            doReturn(Optional.empty())
                .when(pedidoRepository).findById(anyInt());

            assertThrows(
                Exception.class,
                () -> pedidoService.findByIdMandatory(1, false)
            );
        }
    }

    /*@Nested
    class Create
    {
        @Test
        @DisplayName("Deve criar Pedido com sucesso")
        void deveCriarPedidoComSucesso() {
            Pedido p = new Pedido(1, List.of(), false);
            Pedido empty = new Pedido();

            doReturn(p).when(pedidoRepository).save(empty);

            var output = pedidoService.create();

            assertNotNull(output);
            assertEquals(p, output);
        }
    }*/

    /*@Nested
    class FindById
    {

    }*/

    @Nested
    class CalcularTotal
    {
        @Test
        @DisplayName("Deve calcular total com sucesso")
        void deveCalcularTotalComSucesso() throws Exception {
            float total = 10;

            doReturn(total)
                .when(pedidoRepository).calcularTotal(anyInt());

            var output = pedidoService.calcularTotal(1);

            assertEquals(total, output);
        }

        @Test
        @DisplayName("Deve lançar Exception quando ID for invalido")
        void deveLancarExceptionQuandoIdForInvalido() {
            doReturn(-1f)
                .when(pedidoRepository).calcularTotal(anyInt());

            assertThrows(
                Exception.class,
                () -> pedidoService.calcularTotal(1)
            );
        }

        @Test
        @DisplayName("Deve adicionar itens e calcular total com sucesso")
        void deveAdicionarItensECalcularTotalComSucesso() throws Exception {
            float total = 7.11f * 2 + 3.91f * 3 + 77.3f * 1;

            Produto p1 = new Produto(1, "Produto 1", 7.11f),
                    p2 = new Produto(2, "Produto 2", 3.91f),
                    p3 = new Produto(3, "Produto 3", 77.3f);

            List<ItemPedido> itens = List.of(
                new ItemPedido(1, p1, 2),
                new ItemPedido(2, p2, 3)
            );

            Pedido p = new Pedido(1, itens, false);

            doReturn(Optional.of(p))
                .when(pedidoRepository).findById(p.getId());

            doReturn(p3)
                .when(produtoService).findById(p3.getCodigo());

            doReturn(itens.get(0), itens.get(1))
                .when(itemPedidoService).save(any());

            //doReturn(p)
            //    .when(pedidoRepository).save(p);

            //doReturn(total)
            //   .when(pedidoRepository).calcularTotal(p.getId());

            var output = pedidoService.calcularTotal(
                p.getId(),
                List.of(new ItemPedidoDTO(3, 1))
            );

            //System.out.println(output);

            assertEquals(total, output);
        }
    }
}