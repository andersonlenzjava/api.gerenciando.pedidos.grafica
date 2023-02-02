package grafica.pedidos.api.controller.empregadosPedido;

import grafica.pedidos.api.domain.funcionario.empregado.copiador.CopiadorRegister;
import grafica.pedidos.api.domain.pedido.PedidoRegister;
import grafica.pedidos.api.service.pedido.PedidoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/copiador")
public class EmpregadoCopiadorController {

    @Autowired
    private PedidoService pedidoService;

    @PutMapping("/tirarFila/{pedidoId}")
    @Transactional
    public void tirarFilaProduzir(@PathVariable Long pedidoId) {
        return pedidoService.tirarFilaProduzir(pedidoId);
    }

    @PutMapping("/fecharImpressao/{pedidoId}")
    @Transactional
    public void fecharImpressao(@PathVariable Long pedidoId) {
        return pedidoService.fecharImpressao(pedidoId);
    }

}
