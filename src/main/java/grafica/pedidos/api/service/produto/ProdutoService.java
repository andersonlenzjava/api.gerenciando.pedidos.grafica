package grafica.pedidos.api.service.produto;

import grafica.pedidos.api.domain.produto.Produto;
import grafica.pedidos.api.domain.produto.ProdutoRepository;
import grafica.pedidos.api.domain.produto.ProdutoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    //Get
    public Page<ProdutoResponse> listarProdutos(String nomeProduto, Pageable paginacao) {
        if(nomeProduto == null) {
            Page<Produto> produtos = produtoRepository.findAll(paginacao);
            return ProdutoResponse.converter(produtos);
        } else {
            Page<Produto> produto = produtoRepository.findByNameIgnoreCase(nomeProduto, paginacao);
            return ProdutoResponse.converter(produto);
        }
    }


}
