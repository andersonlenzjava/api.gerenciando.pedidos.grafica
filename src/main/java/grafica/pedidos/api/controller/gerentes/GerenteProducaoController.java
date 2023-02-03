package grafica.pedidos.api.controller.gerentes;

import grafica.pedidos.api.domain.funcionario.empregado.copiador.CopiadorRegister;
import grafica.pedidos.api.domain.funcionario.empregado.copiador.CopiadorResponse;
import grafica.pedidos.api.domain.pedido.PedidoResponse;
import grafica.pedidos.api.infra.exeption.ItemInesistenteException;
import grafica.pedidos.api.service.empregado.CopiadorService;
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
@RequestMapping("/funcionario/gerenteProducao")
public class GerenteProducaoController {

//----------------------------------------------------------------------------------
//    Gerenciar Copiador

    @Autowired
    private CopiadorService copiadorService;


    @GetMapping
    public Page<CopiadorResponse> listarCopiador(
            @RequestParam(required = false) String nomeCopiador,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                 Pageable paginacao) {
        return copiadorService.listarCopiador(nomeCopiador, paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CopiadorResponse> buscarCopiador(@PathVariable Long id) {
        return copiadorService.buscarCopiador(id);
    }

    @PostMapping
    public ResponseEntity<CopiadorResponse> cadastrarCopiador(
            @RequestBody @Valid CopiadorRegister copiadorRegister,
            UriComponentsBuilder uriBuilder) throws Exception {
        return copiadorService.cadastrarCopiador(copiadorRegister, uriBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<CopiadorResponse> atualizarCopiador(
            @PathVariable Long id,
            @RequestBody @Valid CopiadorRegister copiadorRegister) {
        return copiadorService.atualizarCopiador(id, copiadorRegister);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarProdutorCopias(@PathVariable Long id) {
        return copiadorService.removerCopiador(id);
    }

//-----------------------------------------------------------------------------------------------
//    Gerenciar Pedidos

    @Autowired
    private PedidoService pedidoService;

    @DeleteMapping("/deletarPedido/{id}")
    @Transactional
    public ResponseEntity<?> cancelarPedido(@PathVariable Long id) throws ItemInesistenteException {
        return pedidoService.cancelarPedido(id);
    }

    @GetMapping("/buscarPedidosFila")
    public Page<PedidoResponse> buscarFilaPedidos(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) throws ItemInesistenteException {
        return pedidoService.buscarFilaPedidos(paginacao);
    }

}
