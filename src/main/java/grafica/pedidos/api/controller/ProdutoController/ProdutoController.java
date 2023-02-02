package grafica.pedidos.api.controller.ProdutoController;

import grafica.pedidos.api.domain.produto.ProdutoRegister;
import grafica.pedidos.api.domain.produto.ProdutoResponse;
import grafica.pedidos.api.service.produto.ProdutoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public Page<ProdutoResponse> listarProdutos(
            @RequestParam(required = false) String nomeProduto,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return produtoService.listarProdutos(nomeProduto, paginacao);
    }

    @GetMapping("/{produtoId}")
    public ResponseEntity<ProdutoResponse> listarProdutosPorId(@PathVariable Long produtoId) {
        return produtoService.listarProdutosPorId(produtoId);
    }

    @PostMapping
    public void cadastrarProduto(
            @RequestBody @Valid ProdutoRegister produtoRegister, UriComponentsBuilder uriBuilder) {
        return produtoService.cadastrarProduto(produtoRegister, uriBuilder);
    }

    @PutMapping("/atualizarDados/{produtoId}")
    @Transactional
    public void atualizarDadosProduto(
            @PathVariable Long produtoId, @RequestBody @Valid ProdutoRegister produtoRegister) {
        return produtoService.atualizarDadosProduto(produtoId, produtoRegister);
    }

// -------------------------------------------------------------------
//    Ativar Desativar

    @PutMapping("/desativar/{produtoId}")
    @Transactional
    public void desativarProduto(
            @PathVariable Long produtoId, @RequestBody @Valid ProdutoRegister produtoRegister) {
        return produtoService.desativarProduto(produtoId, produtoRegister);
    }

    @PutMapping("/ativar/{produtoId}")
    @Transactional
    public void ativarProduto(
            @PathVariable Long produtoId, @RequestBody @Valid ProdutoRegister produtoRegister) {
        return produtoService.ativarProduto(produtoId, produtoRegister);
    }

}
