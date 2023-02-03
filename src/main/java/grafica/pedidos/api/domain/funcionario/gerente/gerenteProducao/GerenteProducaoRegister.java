package grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao;

import grafica.pedidos.api.domain.funcionario.funcionario.FuncionarioRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinanceiro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GerenteProducaoRegister(

        @NotNull
        @Valid
        FuncionarioRegister funcionarioRegister
) {

    public GerenteProducao converter() {
        return new GerenteProducao(
                this.funcionarioRegister.cpf(),
                this.funcionarioRegister.nome(),
                this.funcionarioRegister.dataNascimento(),
                this.funcionarioRegister.telefone());
    }

}
