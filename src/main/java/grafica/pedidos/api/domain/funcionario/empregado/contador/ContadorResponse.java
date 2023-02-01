package grafica.pedidos.api.domain.funcionario.empregado.contador;

import org.springframework.data.domain.Page;

public record ContadorResponse() {
    public static Page<ContadorResponse> converter(Page<Contador> contadores) {
    }

    public static Page<ContadorResponse> converterUmContador(Page<Contador> contador) {
    }
}
