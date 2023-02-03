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
    public ResponseEntity<ProdutoResponse> cadastrarProduto(
            @RequestBody @Valid ProdutoRegister produtoRegister, UriComponentsBuilder uriBuilder) throws Exception {
        return produtoService.cadastrarProduto(produtoRegister, uriBuilder);
    }

    @PutMapping("/{produtoId}")
    @Transactional
    public ResponseEntity<ProdutoResponse> atualizarDadosProduto(
            @PathVariable Long produtoId,
            @RequestBody @Valid ProdutoRegister produtoRegister, UriComponentsBuilder uriBuilder) throws Exception {
        return produtoService.atualizarDadosProduto(produtoId, produtoRegister, uriBuilder);
    }

// -------------------------------------------------------------------
//    Ativar Desativar

    @PutMapping("/ativar/{produtoId}")
    @Transactional
    public ResponseEntity<ProdutoResponse> ativarProduto(
            @PathVariable Long produtoId,
            @RequestBody @Valid ProdutoRegister produtoRegister, UriComponentsBuilder uriBuilder) throws Exception {
        return produtoService.ativarProduto(produtoId, produtoRegister, uriBuilder);
    }

    @PutMapping("/desativar/{produtoId}")
    @Transactional
    public ResponseEntity<ProdutoResponse> desativarProduto(
            @PathVariable Long produtoId,
            @RequestBody @Valid ProdutoRegister produtoRegister, UriComponentsBuilder uriBuilder) throws Exception {
        return produtoService.desativarProduto(produtoId, produtoRegister, uriBuilder);
    }



}
