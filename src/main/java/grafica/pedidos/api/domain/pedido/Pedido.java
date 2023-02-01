package grafica.pedidos.api.domain.pedido;

import grafica.pedidos.api.domain.funcionario.empregado.produtorCopias.ProdutorCopias;
import grafica.pedidos.api.domain.funcionario.empregado.vendedor.Vendedor;
import grafica.pedidos.api.domain.produto.Produto;
import grafica.pedidos.api.domain.statusPedido.StatusPedido;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Pedido {

    private Long id;
    private String nomeCliente;
    private Produto produto;
    private Double quantidade;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataFinalizacao;
    private StatusPedido statusPedido;
    private Vendedor vendedor;
    private ProdutorCopias produtorCopias;


}
