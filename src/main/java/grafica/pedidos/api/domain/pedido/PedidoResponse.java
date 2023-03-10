package grafica.pedidos.api.domain.pedido;

import grafica.pedidos.api.domain.statusPedido.StatusPedido;
import org.springframework.data.domain.Page;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PedidoResponse(
        Long id,
        String nomeCliente,
        String nomeProduto,
        double quantidade,
        BigDecimal valorTotal,
        LocalDateTime dataEmissao,
        LocalDateTime dataFinalizacao,
        StatusPedido statusPedido,
        String nomeVendedor,
        String nomeCopiador
) {

    public PedidoResponse (Pedido pedido) {

        this(pedido.getId(),
                pedido.getNomeCliente(),
                pedido.getProduto().getNome(),
                pedido.getQuantidade(),
                pedido.getValorTotalServico(),
                pedido.getDataEmissao(),
                pedido.getDataFinalizacao(),
                pedido.getStatusPedido(),
                pedido.getVendedor().getFuncionario().getNome(),
                pedido.getCopiador().getFuncionario().getNome());

    }

    public static Page<PedidoResponse> converter(Page<Pedido> pedidos) {
        return pedidos.map(PedidoResponse::new);
    }

    public static PedidoResponse converterUmProduto(Pedido pedido) {
        return new PedidoResponse(pedido);
    }
}
