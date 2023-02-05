package grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas;

import grafica.pedidos.api.domain.funcionario.empregado.vendedor.Vendedor;
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
public class GerenteVendas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Embedded
    private Funcionario funcionario;

    public GerenteVendas(String cpf,
                         String nome,
                         String dataNascimento,
                         String telefone) {
        this.funcionario = new Funcionario (cpf, nome, dataNascimento, telefone);
    }

}
