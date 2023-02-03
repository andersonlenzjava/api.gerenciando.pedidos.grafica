package grafica.pedidos.api.domain.funcionario.funcionario;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    private String cpf;
    private String nome;
    private String dataNascimento;
    private String telefone;
    private StatusTrabalho statusTrabalho;


    public Funcionario(String cpf,
                       String nome,
                       String dataNascimento,
                       String telefone) {
        this.cpf = cpf;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.telefone = telefone;
        this.statusTrabalho = StatusTrabalho.TRABALHANDO;
    }

    public void setStatusTrabalho(String statusTrabalho) {
        this.statusTrabalho = StatusTrabalho.valueOf(statusTrabalho);
    }
}
