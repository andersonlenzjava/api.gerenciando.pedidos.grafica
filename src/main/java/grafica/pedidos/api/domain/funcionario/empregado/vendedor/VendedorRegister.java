package grafica.pedidos.api.domain.funcionario.empregado.vendedor;

import grafica.pedidos.api.domain.funcionario.funcionario.FuncionarioRegister;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record VendedorRegister(

        @NotBlank
        @Valid
        FuncionarioRegister funcionarioRegister
) {
}
