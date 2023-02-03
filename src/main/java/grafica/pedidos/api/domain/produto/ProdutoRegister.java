package grafica.pedidos.api.domain.produto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ProdutoRegister(

        @NotBlank
        String nome,

        @NotBlank
        String codigo,

        @DecimalMin(value = "0.1", inclusive = true)
        @Digits(integer=3, fraction=2)
        BigDecimal valorProduto,

        @NotBlank
        String cpfGerenteVendas,

        @NotBlank
        String cpfGerenteProducao,

        @NotBlank
        String cpfGerenteFinanceiro
) {
        public Produto converter() {
                Produto produto = new Produto(this.nome, this.codigo, this.valorProduto);
                return produto;
        }
}
