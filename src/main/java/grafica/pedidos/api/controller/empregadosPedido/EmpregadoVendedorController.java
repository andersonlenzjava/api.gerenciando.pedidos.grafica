package grafica.pedidos.api.controller.empregadosPedido;

import grafica.pedidos.api.domain.funcionario.empregado.copiador.CopiadorRegister;
import grafica.pedidos.api.domain.pedido.PedidoRegister;
import grafica.pedidos.api.domain.pedido.PedidoResponse;
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

@RestController
@RequestMapping("/vendedor")
public class EmpregadoVendedorController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public Page<PedidoResponse> listarPedidos(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return pedidoService.listarPedidos(paginacao);
    }

    @GetMapping("/{pedidoId}")
    public ResponseEntity<PedidoResponse> listarPedidoPorId(@PathVariable Long pedidoId) {
        return pedidoService.listarPedidoPorId(pedidoId);
    }

    @PostMapping
    public void abrirPedido(@RequestBody @Valid PedidoRegister pedidoRegister, UriComponentsBuilder uriBuilder) {
        return pedidoService.abrirPedido(pedidoRegister, uriBuilder);
    }

    @PutMapping("/atualizarDados/{pedidoId}")
    @Transactional
    public void atualizarDadosPedido(@PathVariable Long pedidoId, @RequestBody @Valid PedidoRegister pedidoRegister) {
        return pedidoService.atualizarDadosPedido(pedidoId, pedidoRegister);
    }

    @PutMapping("/colocarFila/{pedidoId}")
    @Transactional
    public void colocarFilaProducao(@PathVariable Long pedidoId, @RequestBody @Valid PedidoRegister pedidoRegister) {
        return pedidoService.colocarFilaProducao(pedidoId, pedidoRegister);
    }

    @PutMapping("/fechar/{pedidoId}")
    @Transactional
    public ResponseEntity<PedidoResponse> calculaTrocoFechaPedido(
            @PathVariable(required = true) Long pedidoId, UriComponentsBuilder uriBuilder) throws PedidoVazioException {
        return pedidoService.calculaTrocoFechaPedido(pedidoId, uriBuilder);
    }


}
