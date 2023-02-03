package grafica.pedidos.api.domain.funcionario.empregado.copiador;

import grafica.pedidos.api.domain.funcionario.empregado.contador.Contador;
import grafica.pedidos.api.domain.funcionario.funcionario.FuncionarioRegister;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CopiadorRegister(

        @NotNull
        @Valid
        FuncionarioRegister funcionarioRegister
) {
    public Copiador converter() {
        return new Copiador(
                this.funcionarioRegister.cpf(),
                this.funcionarioRegister.nome(),
                this.funcionarioRegister.dataNascimento(),
                this.funcionarioRegister.telefone());
    }
}
