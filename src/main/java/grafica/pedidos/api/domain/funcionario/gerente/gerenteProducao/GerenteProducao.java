package grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao;

import grafica.pedidos.api.domain.funcionario.empregado.copiador.Copiador;
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

    public GerenteProducao(String cpf,
                           String nome,
                           String dataNascimento,
                           String telefone) {
        this.funcionario = new Funcionario (cpf, nome, dataNascimento, telefone);
    }

    public void CancelarPedido(Pedido pedido) {}

    private void CadastrarProdutorCopias(Copiador produtorCopias) {}

}
