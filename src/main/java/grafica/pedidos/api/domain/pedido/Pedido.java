package grafica.pedidos.api.domain.pedido;

import grafica.pedidos.api.domain.funcionario.empregado.copiador.EmpregadoCopiador;
import grafica.pedidos.api.domain.funcionario.empregado.vendedor.EmpregadoVendedor;
import grafica.pedidos.api.domain.produto.Produto;
import grafica.pedidos.api.domain.statusPedido.StatusPedido;

import java.time.LocalDateTime;

public class Pedido {

    private Long id;
    private String nomeCliente;
    private Produto produto;
    private Double quantidade;
    private LocalDateTime dataEmissao;
    private LocalDateTime dataFinalizacao;
    private StatusPedido statusPedido;
    private EmpregadoVendedor vendedor;
    private EmpregadoCopiador produtorCopias;


}
