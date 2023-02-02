package grafica.pedidos.api.domain.produto;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoRegister(

        @NotBlank
        String name,

        @NotBlank
        String codigo,

        @NotBlank
        @Positive
        Double valorProduto,

        @NotBlank
        String cpfGerenteVendas,

        @NotBlank
        String cpfGerenteProducao,

        @NotBlank
        String cpfGerenteFinanceiro
) {
        public Produto converter() {
                BigDecimal valorProd = new BigDecimal(this.valorProduto);
                Produto produto = new Produto(this.name, this.codigo, valorProd);
                return produto;
        }
}
