package grafica.pedidos.api.controller.empregadosPedido;

import grafica.pedidos.api.domain.pedido.PedidoResponse;
import grafica.pedidos.api.service.pedido.PedidoService;
import grafica.pedidos.api.service.produto.ProdutoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contador")
public class EmpregadoContadorController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProdutoService produtoService;

    @PutMapping("/documentar/{pedidoId}")
    @Transactional
    public void documentarPedido(@PathVariable Long pedidoId) {
        return pedidoService.documentarPedido(pedidoId);
    }

    @GetMapping
    public Page<PedidoResponse> listarPedidos(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return pedidoService.listarPedidos(paginacao);
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<PedidoResponse> listarPedidoPorId(@PathVariable Long pedidoId) {
        return pedidoService.listarPedidoPorId(pedidoId);
    }

    @GetMapping("/porCliente/{nomeCliente}")
    public ResponseEntity<PedidoResponse> pedidosPorNomecliente(
            @PathVariable Long nomeCliente,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return pedidoService.pedidosPorNomecliente(nomeCliente, paginacao);
    }

    @GetMapping("/maioresQue/{valorPedido}")
    public ResponseEntity<PedidoResponse> pedidosMaioresQue(
            @PathVariable Long valorPedido,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return pedidoService.pedidosMaioresQue(valorPedido, paginacao);
    }

//    ---------------------------------------------------------------------------
//    Produto

    @GetMapping("/Produto/{nomeProduto}")
    public void listarPedidosPorProduto(
            @PathVariable Long nomeProduto,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return produtoService.listarPedidosPorProduto(nomeProduto, paginacao);
    }

//    ---------------------------------------------------------------------------
//    Busca Dinâmica

//    GetMapping
//    public void buscaDinamica(@PathVariable Long id,
//                                        @RequestBody @Valid CopiadorRegister copiadorRegister) {
//        return copiadorService.atualizarCopiador(id, copiadorRegister);
//    }

}
