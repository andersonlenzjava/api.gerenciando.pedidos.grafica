package grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas;

import org.springframework.data.domain.Page;

public record GerenteVendasResponse() {
    public static Page<GerenteVendasResponse> converter(Page<GerenteVendas> gerentes) {
    }

    public static Page<GerenteVendasResponse> converterUmGerente(Page<GerenteVendas> gerente) {
    }
}
