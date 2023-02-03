package grafica.pedidos.api.domain.funcionario.empregado.copiador;

import grafica.pedidos.api.domain.funcionario.funcionario.StatusTrabalho;
import org.springframework.data.domain.Page;

public record CopiadorResponse(Long id,
                               String cpf,
                               String nome,
                               String dataNascimento,
                               String telefone,
                               StatusTrabalho statusTrabalho) {

    public CopiadorResponse (Copiador copiador) {
        this(copiador.getId(),
                copiador.getFuncionario().getCpf(),
                copiador.getFuncionario().getNome(),
                copiador.getFuncionario().getDataNascimento(),
                copiador.getFuncionario().getTelefone(),
                copiador.getFuncionario().getStatusTrabalho());
    }

    public static Page<CopiadorResponse> converter(Page<Copiador> copiadores) {
        return copiadores.map(CopiadorResponse::new);
    }

    public static CopiadorResponse converterUmCopiador(Copiador copiador) {
        return new CopiadorResponse(copiador);
    }
}
