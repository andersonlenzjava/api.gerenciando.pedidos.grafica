package grafica.pedidos.api.controller.empregadosPedido;

import grafica.pedidos.api.domain.pedido.PedidoResponse;
import grafica.pedidos.api.infra.exeption.ItemInesistenteException;
import grafica.pedidos.api.service.pedido.PedidoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/copiador")
public class EmpregadoCopiadorController {

    @Autowired
    private PedidoService pedidoService;


    @PutMapping("/tirarFila")
    @Transactional
    public ResponseEntity<PedidoResponse> tirarFilaProduzir(
            UriComponentsBuilder uriBuilder) throws ItemInesistenteException {
        return pedidoService.tirarFilaProduzir(uriBuilder);
    }

    @GetMapping
    public Page<PedidoResponse> listarPedidosProduzindo(
            @RequestParam(required = false) String nomeProduto,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) {
        return pedidoService.listarPedidosProduzindo(nomeProduto, paginacao);
    }

    @PutMapping("/fecharImpressao/{pedidoId}")
    @Transactional
    public ResponseEntity<PedidoResponse> fecharImpressao(
            @PathVariable Long pedidoId, UriComponentsBuilder uriBuilder) throws ItemInesistenteException {
        return pedidoService.fecharImpressao(pedidoId, uriBuilder);
    }

}
