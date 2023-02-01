package grafica.pedidos.api.domain.funcionario.empregado.copiador;

import grafica.pedidos.api.domain.funcionario.funcionario.FuncionarioRegister;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CopiadorRegister(

        @NotBlank
        @Valid
        FuncionarioRegister funcionarioRegister
) {
}
