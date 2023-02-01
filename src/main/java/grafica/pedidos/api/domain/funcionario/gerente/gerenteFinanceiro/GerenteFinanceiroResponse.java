package grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro;

import org.springframework.data.domain.Page;

public record GerenteFinanceiroResponse() {

    public static Page<GerenteFinanceiroResponse> converter(Page<GerenteFinanceiro> gerentes) {
    }

    public static Page<GerenteFinanceiroResponse> converterUmGerente(Page<GerenteFinanceiro> gerente) {
    }
}
