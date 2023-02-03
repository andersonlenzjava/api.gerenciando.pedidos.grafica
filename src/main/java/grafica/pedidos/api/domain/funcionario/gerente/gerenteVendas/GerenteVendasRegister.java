package grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas;

import grafica.pedidos.api.domain.funcionario.funcionario.FuncionarioRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducao;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GerenteVendasRegister(

        @NotNull
        @Valid
        FuncionarioRegister funcionarioRegister
) {
    public GerenteVendas converter() {
        return new GerenteVendas(
                this.funcionarioRegister.cpf(),
                this.funcionarioRegister.nome(),
                this.funcionarioRegister.dataNascimento(),
                this.funcionarioRegister.telefone());
    }

}
