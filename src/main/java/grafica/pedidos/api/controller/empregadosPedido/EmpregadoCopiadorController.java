package grafica.pedidos.api.controller.empregadosPedido;

import grafica.pedidos.api.domain.pedido.PedidoResponse;
import grafica.pedidos.api.infra.exeption.ItemInesistenteException;
import grafica.pedidos.api.service.pedido.PedidoService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
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

    // pedidos produzindo

    @PutMapping("/fecharImpressao/{pedidoId}")
    @Transactional
    public void fecharImpressao(@PathVariable Long pedidoId, UriComponentsBuilder uriBuilder) {
        return pedidoService.fecharImpressao(pedidoId, uriBuilder);
    }

}
