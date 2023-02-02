package grafica.pedidos.api.domain.produto;

import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String name,
        String codigo,
        BigDecimal valorProduto
) {

    public ProdutoResponse (Produto produto) {
        this(produto.getId(),
                produto.getName(),
                produto.getCodigo(),
                produto.getValorProduto());
    }

    public static ProdutoResponse converterUmProduto(Produto produto) {
    }

    public static Page<ProdutoResponse> converter(Page<Produto> produto) {
    }
}
