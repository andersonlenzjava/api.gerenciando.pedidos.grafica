package grafica.pedidos.api.domain.produto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoRegister(

        @NotBlank
        String name,

        @NotBlank
        @Positive
        Double valorProduto
) {
}
