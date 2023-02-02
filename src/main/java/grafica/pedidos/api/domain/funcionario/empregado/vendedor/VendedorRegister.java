package grafica.pedidos.api.domain.funcionario.empregado.vendedor;

import grafica.pedidos.api.domain.funcionario.empregado.copiador.Copiador;
import grafica.pedidos.api.domain.funcionario.funcionario.FuncionarioRegister;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

public record VendedorRegister(

        @NotBlank
        @Valid
        FuncionarioRegister funcionarioRegister
) {
    public Vendedor converter() {
        return new Vendedor(
                this.funcionarioRegister.cpf(),
                this.funcionarioRegister.nome(),
                this.funcionarioRegister.dataNascimento(),
                this.funcionarioRegister.telefone());
    }
}
