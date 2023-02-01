package grafica.pedidos.api.domain.funcionario.empregado.vendedor;

import org.springframework.data.domain.Page;

public record VendedorResponse() {
    public static Page<VendedorResponse> converter(Page<Vendedor> vendedores) {
    }

    public static Page<VendedorResponse> converterUmVendedor(Page<Vendedor> vendedor) {
    }
}
