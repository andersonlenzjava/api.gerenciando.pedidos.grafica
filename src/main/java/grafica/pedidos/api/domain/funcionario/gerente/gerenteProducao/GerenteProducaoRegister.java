package grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao;

import grafica.pedidos.api.domain.funcionario.funcionario.FuncionarioRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinanceiro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record GerenteProducaoRegister(

        @NotBlank
        @Valid
        FuncionarioRegister funcionarioRegister
) {

    public GerenteProducao converter() {
        return new GerenteProducao(
                this.funcionarioRegister.cpf(),
                this.funcionarioRegister.nome(),
                this.funcionarioRegister.dataNascimento(),
                this.funcionarioRegister.telefone(),
                this.funcionarioRegister.statusTrabalho());
    }

}
