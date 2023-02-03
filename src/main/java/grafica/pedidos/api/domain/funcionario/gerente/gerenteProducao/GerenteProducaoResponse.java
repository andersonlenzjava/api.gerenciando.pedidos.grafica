package grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao;

import grafica.pedidos.api.domain.funcionario.funcionario.StatusTrabalho;
import org.springframework.data.domain.Page;

public record GerenteProducaoResponse(Long id,
                                      String cpf,
                                      String nome,
                                      String dataNascimento,
                                      String telefone,
                                      StatusTrabalho statusTrabalho) {

    public GerenteProducaoResponse (GerenteProducao gerenteProducao) {
        this(gerenteProducao.getId(),
                gerenteProducao.getFuncionario().getCpf(),
                gerenteProducao.getFuncionario().getNome(),
                gerenteProducao.getFuncionario().getDataNascimento(),
                gerenteProducao.getFuncionario().getTelefone(),
                gerenteProducao.getFuncionario().getStatusTrabalho());
    }


    public static Page<GerenteProducaoResponse> converter(Page<GerenteProducao> gerentes) {
        return gerentes.map(GerenteProducaoResponse::new);
    }


    public static GerenteProducaoResponse converterUmGerente(GerenteProducao gerenteProducao) {
        return new GerenteProducaoResponse(gerenteProducao);
    }
}
