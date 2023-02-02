package grafica.pedidos.api.domain.pedido;

import grafica.pedidos.api.domain.statusPedido.StatusPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Queue;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    Page<Pedido> findByNomeClienteIgnoreCase(String nomeCliente, Pageable paginacao);


    Queue<Pedido> findByStatusPedidoFila(StatusPedido statusPedido);
}
