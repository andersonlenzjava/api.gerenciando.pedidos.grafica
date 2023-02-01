package grafica.pedidos.api.domain.produto;

import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinanceiro;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducao;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas.GerenteVendas;

import java.math.BigDecimal;

public class Produto {

    private Long id;
    private String name;
    private BigDecimal valorProducao;

    private void CadastrarProduto(
            GerenteVendas gerenteVendas,
            GerenteProducao gerenteProducao,
            GerenteFinanceiro gerenteFinanceiro) {

    }

}
