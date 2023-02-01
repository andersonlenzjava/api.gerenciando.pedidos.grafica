package grafica.pedidos.api.controller.empregadosPedido;

import grafica.pedidos.api.domain.funcionario.empregado.copiador.CopiadorRegister;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

public class EmpregadoContadorController {



    @PostMapping
    @Transactional
    public void registrarPedido(@PathVariable Long id,
                                  @RequestBody @Valid CopiadorRegister copiadorRegister) {
        return copiadorService.atualizarCopiador(id, copiadorRegister);
    }

    GetMapping
    public void pedidosPorNomecliente(@PathVariable Long id,
                                @RequestBody @Valid CopiadorRegister copiadorRegister) {
        return copiadorService.atualizarCopiador(id, copiadorRegister);
    }

    GetMapping
    public void pedidosMaioresQue(@PathVariable Long id,
                                      @RequestBody @Valid CopiadorRegister copiadorRegister) {
        return copiadorService.atualizarCopiador(id, copiadorRegister);
    }

    GetMapping
    public void listarProdutos(@PathVariable Long id,
                                  @RequestBody @Valid CopiadorRegister copiadorRegister) {
        return copiadorService.atualizarCopiador(id, copiadorRegister);
    }

    GetMapping
    public void listarPedidosPorProduto(@PathVariable Long id,
                               @RequestBody @Valid CopiadorRegister copiadorRegister) {
        return copiadorService.atualizarCopiador(id, copiadorRegister);
    }

    GetMapping
    public void buscaDinamica(@PathVariable Long id,
                                        @RequestBody @Valid CopiadorRegister copiadorRegister) {
        return copiadorService.atualizarCopiador(id, copiadorRegister);
    }

}
