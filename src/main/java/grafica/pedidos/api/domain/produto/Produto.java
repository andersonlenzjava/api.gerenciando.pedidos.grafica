package grafica.pedidos.api.domain.produto;

import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinanceiro;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducao;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas.GerenteVendas;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal valorProduto;

    private void CadastrarProduto(
            GerenteVendas gerenteVendas,
            GerenteProducao gerenteProducao,
            GerenteFinanceiro gerenteFinanceiro) {

    }

}
