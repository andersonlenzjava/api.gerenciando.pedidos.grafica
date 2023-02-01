package grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao;

import grafica.pedidos.api.domain.funcionario.funcionario.StatusTrabalho;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public record GerenteProducaoResponse(Long id,
                                      String nome,
                                      LocalDate dataNascimento,
                                      String telefone,
                                      StatusTrabalho statusTrabalho) {

    public GerenteProducaoResponse (GerenteProducao gerenteProducao) {
        this(gerenteProducao.getId(),
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
