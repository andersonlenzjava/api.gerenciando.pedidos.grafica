package grafica.pedidos.api.controller.empregadosPedido;

import grafica.pedidos.api.domain.pedido.PedidoResponse;
import grafica.pedidos.api.infra.exeption.ItemInesistenteException;
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
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;

@RestController
@RequestMapping("/contador")
public class EmpregadoContadorController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public Page<PedidoResponse> listarPedidosPagoFinalizado(
            @RequestParam(required = false) String nomeProduto,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return pedidoService.listarPedidosPagoFinalizado(nomeProduto, paginacao);
    }

    @PutMapping("/documentar/{pedidoId}")
    @Transactional
    public ResponseEntity<PedidoResponse> documentarPedido(
            @PathVariable Long pedidoId, UriComponentsBuilder uriBuilder) throws ItemInesistenteException {
        return pedidoService.documentarPedido(pedidoId, uriBuilder);
    }

    @GetMapping("/porCliente/{nomeCliente}")
    public Page<PedidoResponse> pedidosPorNomecliente(
            @PathVariable String nomeCliente,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao)
            throws ItemInesistenteException {
        return pedidoService.pedidosPorNomeCliente(nomeCliente, paginacao);
    }

    @GetMapping("/maioresQue/{valorPedido}")
    public Page<PedidoResponse> pedidosMaioresQue(
            @PathVariable BigDecimal valorPedido,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao)
            throws ItemInesistenteException {
        return pedidoService.pedidosMaioresQue(valorPedido, paginacao);
    }

//    ---------------------------------------------------------------------------
//    Produto

    @GetMapping("/Produto/{nomeProduto}")
    public Page<PedidoResponse> listarPedidosPorProduto(
            @PathVariable String nomeProduto,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao)
            throws ItemInesistenteException {
        return pedidoService.listarPedidosPorProduto(nomeProduto, paginacao);
    }

//    ---------------------------------------------------------------------------
//    Busca Dinâmica

//    GetMapping
//    public void buscaDinamica(@PathVariable Long id,
//                                        @RequestBody @Valid CopiadorRegister copiadorRegister) {
//        return copiadorService.atualizarCopiador(id, copiadorRegister);
//    }

}
