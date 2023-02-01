package grafica.pedidos.api.domain.funcionario.empregado.copiador;

import grafica.pedidos.api.domain.funcionario.empregado.contador.Contador;
import grafica.pedidos.api.domain.funcionario.funcionario.FuncionarioRegister;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record CopiadorRegister(

        @NotBlank
        @Valid
        FuncionarioRegister funcionarioRegister
) {
    public Copiador converter() {
        return new Copiador(
                this.funcionarioRegister.cpf(),
                this.funcionarioRegister.nome(),
                this.funcionarioRegister.dataNascimento(),
                this.funcionarioRegister.telefone(),
                this.funcionarioRegister.statusTrabalho());
    }
}
