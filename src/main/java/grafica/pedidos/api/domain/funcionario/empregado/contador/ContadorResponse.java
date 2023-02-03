package grafica.pedidos.api.domain.funcionario.empregado.contador;

import grafica.pedidos.api.domain.funcionario.funcionario.StatusTrabalho;
import org.springframework.data.domain.Page;

public record ContadorResponse(Long id,
                               String cpf,
                               String nome,
                               String dataNascimento,
                               String telefone,
                               StatusTrabalho statusTrabalho) {

    public ContadorResponse (Contador contador) {
        this(contador.getId(),
                contador.getFuncionario().getCpf(),
                contador.getFuncionario().getNome(),
                contador.getFuncionario().getDataNascimento(),
                contador.getFuncionario().getTelefone(),
                contador.getFuncionario().getStatusTrabalho());
    }

    public static Page<ContadorResponse> converter(Page<Contador> contadores) {
        return contadores.map(ContadorResponse::new);
    }

    public static ContadorResponse converterUmContador(Contador contador) {
        return new ContadorResponse(contador);
    }
}
