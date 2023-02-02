package grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro;

import grafica.pedidos.api.domain.funcionario.funcionario.FuncionarioRegister;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record GerenteFinaceiroRegister(

        @NotBlank
        @Valid
        FuncionarioRegister funcionarioRegister
) {
        public GerenteFinanceiro converter() {
                return new GerenteFinanceiro(
                        this.funcionarioRegister.cpf(),
                        this.funcionarioRegister.nome(),
                        this.funcionarioRegister.dataNascimento(),
                        this.funcionarioRegister.telefone());
        }

}
