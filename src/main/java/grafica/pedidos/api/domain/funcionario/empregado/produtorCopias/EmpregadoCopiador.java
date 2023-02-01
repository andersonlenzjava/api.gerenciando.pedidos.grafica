package grafica.pedidos.api.domain.funcionario.empregado.produtorCopias;

import grafica.pedidos.api.domain.funcionario.funcionario.Funcionario;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class EmpregadoCopiador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Funcionario funcionario;

    private void TirarFila() {

    }

    private void Produzir() {

    }

    private void FecharImpressao() {

    }

}
