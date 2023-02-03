package grafica.pedidos.api.domain.funcionario.empregado.vendedor;

import grafica.pedidos.api.domain.funcionario.funcionario.StatusTrabalho;
import org.springframework.data.domain.Page;

import java.time.LocalDate;

public record VendedorResponse(Long id,
                               String nome,
                               String dataNascimento,
                               String telefone,
                               StatusTrabalho statusTrabalho) {

    public VendedorResponse (Vendedor vendedor) {
        this(vendedor.getId(),
                vendedor.getFuncionario().getNome(),
                vendedor.getFuncionario().getDataNascimento(),
                vendedor.getFuncionario().getTelefone(),
                vendedor.getFuncionario().getStatusTrabalho());
    }

    public static Page<VendedorResponse> converter(Page<Vendedor> vendedores) {
        return vendedores.map(VendedorResponse::new);
    }

    public static VendedorResponse converterUmVendedor(Vendedor vendedor) {
        return new VendedorResponse(vendedor);
    }
}
