package grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas;

import grafica.pedidos.api.domain.funcionario.funcionario.FuncionarioRegister;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record GerenteVendasRegister(

        @NotBlank
        @Valid
        FuncionarioRegister funcionarioRegister
) {
}
