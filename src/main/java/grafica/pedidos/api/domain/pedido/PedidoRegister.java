package grafica.pedidos.api.domain.pedido;

import com.fasterxml.jackson.annotation.JsonFormat;
import grafica.pedidos.api.domain.funcionario.empregado.copiador.EmpregadoCopiador;
import grafica.pedidos.api.domain.funcionario.empregado.vendedor.EmpregadoVendedor;
import grafica.pedidos.api.domain.produto.Produto;
import grafica.pedidos.api.domain.statusPedido.StatusPedido;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;

public record PedidoRegister(

        @NotBlank
        String nomeCliente,

        @NotBlank
        @Positive
        Long produtoId,

        @NotBlank
        @Positive
        Double quantidade,

        @NotBlank
        @Positive
        Long vendedorId,

        @NotBlank
        @Positive
        Long produtorCopiasId
) {
}
