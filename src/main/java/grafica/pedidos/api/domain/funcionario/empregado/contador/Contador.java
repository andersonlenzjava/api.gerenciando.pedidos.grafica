package grafica.pedidos.api.domain.funcionario.empregado.contador;

import grafica.pedidos.api.domain.funcionario.funcionario.Funcionario;
import grafica.pedidos.api.domain.funcionario.funcionario.StatusTrabalho;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Contador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Funcionario funcionario;

    public Contador(String cpf,
                    String nome,
                    String dataNascimento,
                    String telefone) {
        this.funcionario = new Funcionario (cpf, nome, dataNascimento, telefone);

    }

    private void RegistrarPedido() {
    }

    private void Relatorios() {
    }

}
