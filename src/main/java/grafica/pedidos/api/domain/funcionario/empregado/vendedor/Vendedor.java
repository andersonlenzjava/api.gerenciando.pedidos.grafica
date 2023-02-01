package grafica.pedidos.api.domain.funcionario.empregado.vendedor;

import grafica.pedidos.api.domain.funcionario.funcionario.Funcionario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Vendedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Funcionario funcionario;

    public Vendedor(String cpf,
                    String nome,
                    String dataNascimento,
                    String telefone,
                    String statusTrabalho) {
    }


    private void AbrirPedido() {
    }

    private void ColocarFila() {
    }

    private void FecharPedido() {
    }

}
