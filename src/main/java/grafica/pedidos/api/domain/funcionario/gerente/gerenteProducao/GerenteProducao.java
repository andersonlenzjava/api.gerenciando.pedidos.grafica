package grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao;

import grafica.pedidos.api.domain.funcionario.empregado.produtorCopias.EmpregadoCopiador;
import grafica.pedidos.api.domain.funcionario.funcionario.Funcionario;
import grafica.pedidos.api.domain.pedido.Pedido;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class GerenteProducao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Funcionario funcionario;

    public void CancelarPedido(Pedido pedido) {}

    private void CadastrarProdutorCopias(EmpregadoCopiador produtorCopias) {}

}
