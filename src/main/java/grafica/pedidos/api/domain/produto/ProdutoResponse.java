package grafica.pedidos.api.domain.produto;

import org.springframework.data.domain.Page;

import java.math.BigDecimal;

public record ProdutoResponse(
        Long id,
        String nome,
        String codigo,
        BigDecimal valorProduto
) {

    public ProdutoResponse (Produto produto) {
        this(produto.getId(),
                produto.getNome(),
                produto.getCodigo(),
                produto.getValorProduto());
    }

    public static Page<ProdutoResponse> converter(Page<Produto> produtos) {
        return produtos.map(ProdutoResponse::new);
    }

    public static ProdutoResponse converterUmProduto(Produto produto) {
        return new ProdutoResponse(produto);
    }

}
