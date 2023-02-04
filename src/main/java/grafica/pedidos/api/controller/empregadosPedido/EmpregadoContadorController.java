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

    @GetMapping("/listarPedidosRegistrado")
    public Page<PedidoResponse> listarPedidosRegistrado(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao)
            throws ItemInesistenteException {
        return pedidoService.listarPedidosRegistrado(paginacao);
    }

    @PutMapping("/registrar/{pedidoId}")
    @Transactional
    public ResponseEntity<PedidoResponse> registrarPedido(
            @PathVariable Long pedidoId, UriComponentsBuilder uriBuilder) throws ItemInesistenteException {
        return pedidoService.registrarPedido(pedidoId, uriBuilder);
    }

    @GetMapping("/porCliente/")
    public Page<PedidoResponse> pedidosPorNomecliente(
            @RequestParam(required = true) String nomeCliente,
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

    @GetMapping("/Produto/")
    public Page<PedidoResponse> listarPedidosPorProduto(
            @RequestParam(required = true) String nomeProduto,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao)
            throws ItemInesistenteException {
        return pedidoService.listarPedidosPorProduto(nomeProduto, paginacao);
    }

}
