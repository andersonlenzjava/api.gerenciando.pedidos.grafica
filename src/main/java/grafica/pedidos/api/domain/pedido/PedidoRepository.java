package grafica.pedidos.api.domain.pedido;

import grafica.pedidos.api.domain.statusPedido.StatusPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT u FROM Pedido u WHERE u.nomeCliente = :nomeCliente ")
    Page<Pedido> findByNomeClienteIgnoreCase(@Param("nomeCliente") String nomeCliente, Pageable paginacao);

    @Query("SELECT u FROM Pedido u WHERE u.statusPedido = :status ")
    List<Pedido> findByStatusPedidoFila(@Param("status") StatusPedido statusPedido);

    @Query("SELECT u FROM Pedido u WHERE u.statusPedido = :status ")
    Page<Pedido> findByStatusPedido(@Param("status") StatusPedido statusPedido, Pageable paginacao);

    @Query("SELECT u FROM Pedido u WHERE u.produto.nome = :nomeProduto ")
    Page<Pedido> findByProdutoNameIgnoreCase(@Param("nomeProduto") String nomeProduto, Pageable paginacao);

    @Query("SELECT u FROM Pedido u WHERE u.produto.nome = :nomeProduto AND u.statusPedido=:status")
    Page<Pedido> findByProdutoNameIgnoreCaseAndStatusPedido(
            @Param("nomeProduto") String nomeProduto, @Param("status") StatusPedido statusPedido, Pageable paginacao);

    @Query("SELECT u FROM Pedido u WHERE u.valorTotalServico >= :valorPedido")
    Page<Pedido> findMaiorQue(@Param("valorPedido") BigDecimal valorPedido, Pageable paginacao);


}
