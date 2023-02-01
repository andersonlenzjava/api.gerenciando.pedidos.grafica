package grafica.pedidos.api.domain.funcionario.chefeGeral;

import grafica.pedidos.api.domain.funcionario.funcionario.FuncionarioRegister;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record ChefeGeralRegister(

        @NotBlank
        @Valid
        FuncionarioRegister funcionarioRegister
) {
}
