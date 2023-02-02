package grafica.pedidos.api.domain.funcionario.chefeGeral;

import grafica.pedidos.api.domain.funcionario.funcionario.FuncionarioRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinanceiro;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record ChefeGeralRegister(

        @NotBlank
        @Valid
        FuncionarioRegister funcionarioRegister
) {
        public ChefeGeral converter() {
                return new ChefeGeral(
                        this.funcionarioRegister.cpf(),
                        this.funcionarioRegister.nome(),
                        this.funcionarioRegister.dataNascimento(),
                        this.funcionarioRegister.telefone());
        }
}
