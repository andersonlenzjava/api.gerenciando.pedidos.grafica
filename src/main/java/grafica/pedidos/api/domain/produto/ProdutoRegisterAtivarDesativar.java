package grafica.pedidos.api.domain.produto;

import jakarta.validation.constraints.NotBlank;

public record ProdutoRegisterAtivarDesativar(

        @NotBlank
        String cpfGerenteVendas,

        @NotBlank
        String cpfGerenteProducao,

        @NotBlank
        String cpfGerenteFinanceiro
) {
}
