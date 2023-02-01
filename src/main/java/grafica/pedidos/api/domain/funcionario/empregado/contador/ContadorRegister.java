package grafica.pedidos.api.domain.funcionario.empregado.contador;

import grafica.pedidos.api.domain.funcionario.funcionario.FuncionarioRegister;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record ContadorRegister(

        @NotBlank
        @Valid
        FuncionarioRegister funcionarioRegister
) {
    public Contador converter() {
        return new Contador(
                this.funcionarioRegister.cpf(),
                this.funcionarioRegister.nome(),
                this.funcionarioRegister.dataNascimento(),
                this.funcionarioRegister.telefone());
    }
}
