package grafica.pedidos.api.domain.pedido;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record PedidoRegister(

        @NotBlank
        String nomeCliente,

        @NotNull
        @Positive
        Long produtoId,

        @NotNull
        @Digits(integer=4, fraction=0)
        double quantidade,

        @NotNull
        @Positive
        Long vendedorId

) {
}
