package grafica.pedidos.api.controller.empregadosPedido;

import grafica.pedidos.api.domain.funcionario.empregado.copiador.CopiadorRegister;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

public class EmpregadoVendedor {



    @GetMapping
    public Page<PedidoResponse> listarPedidos(@PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                              Pageable paginacao) {
        return pedidoService.listarPedidos(paginacao);
    }

    @GetMapping("/porId/{pedidoId}")
    public ResponseEntity<PedidoResponse> listarPedidoPorId(@PathVariable Long pedidoId) {
        return pedidoService.listarPedidoPorId(pedidoId);
    }

    @PostMapping
    public void abrirPedido(@RequestBody @Valid CopiadorRegister copiadorRegister,
                                  UriComponentsBuilder uriBuilder) {
        return copiadorService.cadastrarCopiador(copiadorRegister, uriBuilder);
    }

    @PutMapping
    @Transactional
    public void atualizarDadosPedido(@PathVariable Long id,
                                  @RequestBody @Valid CopiadorRegister copiadorRegister) {
        return copiadorService.atualizarCopiador(id, copiadorRegister);
    }

    @PutMapping
    @Transactional
    public void colocarFilaProducao(@PathVariable Long id,
                                     @RequestBody @Valid CopiadorRegister copiadorRegister) {
        return copiadorService.atualizarCopiador(id, copiadorRegister);
    }

    @PutMapping("/atribuiProcessando/{pedidoId}")
    @Transactional
    public ResponseEntity<PedidoResponse> calculaTrocoFechaPedido(@PathVariable(required = true) Long pedidoId, UriComponentsBuilder uriBuilder) throws PedidoVazioException {
        return pedidoService.atribuiProcessando(pedidoId, uriBuilder);
    }


}
