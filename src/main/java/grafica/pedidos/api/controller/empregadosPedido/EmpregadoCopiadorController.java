package grafica.pedidos.api.controller.empregadosPedido;

import grafica.pedidos.api.domain.funcionario.empregado.copiador.CopiadorRegister;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

public class EmpregadoCopiadorController {


    @PutMapping
    @Transactional
    public void tirarFilaProduzir(@PathVariable Long id,
                                     @RequestBody @Valid CopiadorRegister copiadorRegister) {
        return copiadorService.atualizarCopiador(id, copiadorRegister);
    }

    @PutMapping
    @Transactional
    public void fecharImpressao(@PathVariable Long id,
                                  @RequestBody @Valid CopiadorRegister copiadorRegister) {
        return copiadorService.atualizarCopiador(id, copiadorRegister);
    }

}
