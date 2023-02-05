package grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro;

import grafica.pedidos.api.domain.funcionario.empregado.contador.Contador;
import grafica.pedidos.api.domain.funcionario.funcionario.Funcionario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class GerenteFinanceiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Funcionario funcionario;

    public GerenteFinanceiro(String cpf,
                             String nome,
                             String dataNascimento,
                             String telefone) {
        this.funcionario = new Funcionario (cpf, nome, dataNascimento, telefone);
    }

}
