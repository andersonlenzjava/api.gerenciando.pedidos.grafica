package grafica.pedidos.api.domain.funcionario.empregado.copiador;

import grafica.pedidos.api.domain.funcionario.funcionario.Funcionario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Copiador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Funcionario funcionario;

    public Copiador(String cpf,
                    String nome,
                    String dataNascimento,
                    String telefone,
                    String statusTrabalho) {
    }

    private void TirarFila() {

    }

    private void Produzir() {

    }

    private void FecharImpressao() {

    }

}
