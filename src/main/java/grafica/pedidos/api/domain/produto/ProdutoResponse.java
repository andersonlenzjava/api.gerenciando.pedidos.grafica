package grafica.pedidos.api.domain.produto;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String name,
        BigDecimal valorProduto
) {

    public ProdutoResponse (Produto produto) {
        this(produto.getId(),
                produto.getName(),
                produto.getValorProduto());
    }
}
