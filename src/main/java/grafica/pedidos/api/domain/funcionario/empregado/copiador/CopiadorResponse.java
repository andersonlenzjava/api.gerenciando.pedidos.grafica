package grafica.pedidos.api.domain.funcionario.empregado.copiador;

import org.springframework.data.domain.Page;

public record CopiadorResponse() {
    public static Page<CopiadorResponse> converter(Page<Copiador> copiadores) {
    }

    public static Page<CopiadorResponse> converterUmCopiador(Page<Copiador> copiador) {
    }
}
