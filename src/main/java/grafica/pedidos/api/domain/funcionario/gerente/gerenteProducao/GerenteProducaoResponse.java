package grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao;

import org.springframework.data.domain.Page;

public record GerenteProducaoResponse() {
    public static Page<GerenteProducaoResponse> converter(Page<GerenteProducao> gerentes) {
    }

    public static Page<GerenteProducaoResponse> converterUmGerente(Page<GerenteProducao> gerente) {
    }
}
