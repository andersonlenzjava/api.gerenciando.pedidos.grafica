package grafica.pedidos.api.domain.funcionario.empregado.vendedor;

import grafica.pedidos.api.domain.funcionario.empregado.contador.Contador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Long> {
    Page<Vendedor> findByFuncionarioNomeIgnoreCase(String nomeVendedor, Pageable paginacao);

    Optional<Vendedor> findByFuncionarioCpfIgnoreCase(String cpf);
}
