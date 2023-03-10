package grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas;

import grafica.pedidos.api.domain.funcionario.funcionario.StatusTrabalho;
import org.springframework.data.domain.Page;

public record GerenteVendasResponse(Long id,
                                    String cpf,
                                    String nome,
                                    String dataNascimento,
                                    String telefone,
                                    StatusTrabalho statusTrabalho) {

    public GerenteVendasResponse (GerenteVendas gerenteVendas) {
        this(gerenteVendas.getId(),
                gerenteVendas.getFuncionario().getCpf(),
                gerenteVendas.getFuncionario().getNome(),
                gerenteVendas.getFuncionario().getDataNascimento(),
                gerenteVendas.getFuncionario().getTelefone(),
                gerenteVendas.getFuncionario().getStatusTrabalho());
    }

    public static Page<GerenteVendasResponse> converter(Page<GerenteVendas> gerentes) {
        return gerentes.map(GerenteVendasResponse::new);
    }


    public static GerenteVendasResponse converterUmGerente(GerenteVendas gerenteVendas) {
        return new GerenteVendasResponse(gerenteVendas);
    }
}
