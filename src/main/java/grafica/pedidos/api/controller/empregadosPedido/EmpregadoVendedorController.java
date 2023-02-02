package grafica.pedidos.api.controller.empregadosPedido;

import grafica.pedidos.api.domain.pedido.PedidoRegister;
import grafica.pedidos.api.domain.pedido.PedidoResponse;
import grafica.pedidos.api.infra.exeption.ItemInesistenteException;
import grafica.pedidos.api.infra.exeption.PedidoInalteravelException;
import grafica.pedidos.api.infra.exeption.ValorPagoInsuficienteException;
import grafica.pedidos.api.service.pedido.PedidoService;
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

import java.math.BigDecimal;

@RestController
@RequestMapping("/vendedor")
public class EmpregadoVendedorController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public Page<PedidoResponse> listarPedidos(
            @RequestParam(required = false) String nomeProduto,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return pedidoService.listarPedidos(nomeProduto, paginacao);
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<PedidoResponse> listarPedidoPorId(@PathVariable Long pedidoId) {
        return pedidoService.listarPedidoPorId(pedidoId);
    }

    @PostMapping
    public ResponseEntity<PedidoResponse> abrirPedido(
            @RequestBody @Valid PedidoRegister pedidoRegister, UriComponentsBuilder uriBuilder) {
        return pedidoService.abrirPedido(pedidoRegister, uriBuilder);
    }

    @PutMapping("/atualizarDados/{pedidoId}")
    @Transactional
    public ResponseEntity<PedidoResponse> atualizarDadosPedido(
            @PathVariable Long pedidoId,
            @RequestBody @Valid PedidoRegister pedidoRegister, UriComponentsBuilder uriBuilder)
            throws PedidoInalteravelException, ItemInesistenteException {
        return pedidoService.atualizarDadosPedido(pedidoId, pedidoRegister, uriBuilder);
    }

    @PutMapping("/colocarFila/{pedidoId}")
    @Transactional
    public ResponseEntity<PedidoResponse> colocarFilaProducao(
            @PathVariable Long pedidoId, UriComponentsBuilder uriBuilder)
            throws PedidoInalteravelException, ItemInesistenteException {
        return pedidoService.colocarFilaProducao(pedidoId,  uriBuilder);
    }

    @PutMapping("/fechar/{pedidoId}")
    @Transactional
    public ResponseEntity<BigDecimal> calculaTrocoFechaPedido(
            @PathVariable(required = true) Long pedidoId,
            @RequestParam(required = true)BigDecimal valorPago) throws ValorPagoInsuficienteException, PedidoInalteravelException, ItemInesistenteException {
        return pedidoService.calculaTrocoFechaPedido(pedidoId, valorPago);
    }


}
