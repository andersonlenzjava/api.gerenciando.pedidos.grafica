package grafica.pedidos.api.domain.funcionario.funcionario;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Funcionario {

    private String nome;
    private String cpf;
    private LocalDate dataNascimento;
    private String telefone;
    private StatusTrabalho statusTrabalho;


}
