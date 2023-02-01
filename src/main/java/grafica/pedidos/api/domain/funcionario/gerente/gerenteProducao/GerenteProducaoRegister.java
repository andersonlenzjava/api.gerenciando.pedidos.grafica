package grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao;

import grafica.pedidos.api.domain.funcionario.funcionario.FuncionarioRegister;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record GerenteProducaoRegister(

        @NotBlank
        @Valid
        FuncionarioRegister funcionarioRegister
) {
}
