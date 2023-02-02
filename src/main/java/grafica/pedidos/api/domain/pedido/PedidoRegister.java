package grafica.pedidos.api.domain.pedido;

import grafica.pedidos.api.domain.funcionario.empregado.contador.Contador;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

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
        Long vendedorId

) {
}
