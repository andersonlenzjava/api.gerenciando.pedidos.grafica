package grafica.pedidos.api.domain.funcionario.funcionario;

import grafica.pedidos.api.domain.funcionario.statusTrabalho.StatusTrabalho;

import java.time.LocalDate;

public class Funcionario {

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private StatusTrabalho statusTrabalho;

}
