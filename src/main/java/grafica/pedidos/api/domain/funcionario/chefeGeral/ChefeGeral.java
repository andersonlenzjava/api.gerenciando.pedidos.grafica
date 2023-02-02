package grafica.pedidos.api.domain.funcionario.chefeGeral;

import grafica.pedidos.api.domain.funcionario.funcionario.Funcionario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ChefeGeral {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Funcionario funcionario;

    public ChefeGeral(String cpf,
                             String nome,
                             String dataNascimento,
                             String telefone) {
        this.funcionario = new Funcionario (cpf, nome, dataNascimento, telefone);
    }

}
