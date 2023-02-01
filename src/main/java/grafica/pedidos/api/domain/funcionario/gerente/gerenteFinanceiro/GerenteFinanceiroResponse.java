package grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro;

import grafica.pedidos.api.domain.funcionario.funcionario.StatusTrabalho;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public record GerenteFinanceiroResponse(Long id,
                                        String nome,
                                        LocalDate dataNascimento,
                                        String telefone,
                                        StatusTrabalho statusTrabalho) {

    public GerenteFinanceiroResponse (GerenteFinanceiro gerenteFinanceiro) {
        this(gerenteFinanceiro.getId(),
                gerenteFinanceiro.getFuncionario().getNome(),
                gerenteFinanceiro.getFuncionario().getDataNascimento(),
                gerenteFinanceiro.getFuncionario().getTelefone(),
                gerenteFinanceiro.getFuncionario().getStatusTrabalho());
    }

    public static Page<GerenteFinanceiroResponse> converter(Page<GerenteFinanceiro> gerentes) {
        return gerentes.map(GerenteFinanceiroResponse::new);
    }

    public static GerenteFinanceiroResponse converterUmGerente(GerenteFinanceiro gerenteFinanceiro) {
        return new GerenteFinanceiroResponse(gerenteFinanceiro);
    }
}
