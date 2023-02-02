package grafica.pedidos.api.domain.pedido;

import grafica.pedidos.api.domain.statusPedido.StatusPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Queue;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Page<Pedido> findByNomeClienteIgnoreCase(String nomeCliente, Pageable paginacao);


    Queue<Pedido> findByStatusPedidoFila(StatusPedido statusPedido);

    Page<Pedido> findByStatusPedido(StatusPedido statusPedido, Pageable paginacao);

    Page<Pedido> findByProdutoNameIgnoreCase(String nomeProduto, Pageable paginacao);

    Page<Pedido> findByProdutoNameIgnoreCaseAndStatusPedido(
            String nomeProduto, StatusPedido statusPedido, Pageable paginacao);

    @Query("SELECT u FROM Pedido u WHERE u.valorTotalServico >= :valorPedido")
    Page<Pedido> findMaiorQue(BigDecimal valorPedido, Pageable paginacao);


}
