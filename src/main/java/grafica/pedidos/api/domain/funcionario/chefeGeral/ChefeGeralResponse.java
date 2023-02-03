package grafica.pedidos.api.domain.funcionario.chefeGeral;

import grafica.pedidos.api.domain.funcionario.funcionario.StatusTrabalho;
import org.springframework.data.domain.Page;

public record ChefeGeralResponse(Long id,
                                 String cpf,
                                 String nome,
                                 String dataNascimento,
                                 String telefone,
                                 StatusTrabalho statusTrabalho) {

    public ChefeGeralResponse(ChefeGeral chefeGeral) {
        this(chefeGeral.getId(),
                chefeGeral.getFuncionario().getCpf(),
                chefeGeral.getFuncionario().getNome(),
                chefeGeral.getFuncionario().getDataNascimento(),
                chefeGeral.getFuncionario().getTelefone(),
                chefeGeral.getFuncionario().getStatusTrabalho());
    }

    public static Page<ChefeGeralResponse> converter(Page<ChefeGeral> chefes) {
        return chefes.map(ChefeGeralResponse::new);
    }

    public static ChefeGeralResponse converterUmChefeGeral(ChefeGeral chefeGeral) {
        return new ChefeGeralResponse(chefeGeral);
    }
}
