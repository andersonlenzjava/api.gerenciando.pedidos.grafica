package grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas;

import grafica.pedidos.api.domain.funcionario.empregado.contador.Contador;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GerenteVendasRepository extends JpaRepository<GerenteVendas, Long> {

    Page<GerenteVendas> findByFuncionarioNomeIgnoreCase(String nomeGerente, Pageable paginacao);

    Optional<GerenteVendas> findByFuncionarioCpfIgnoreCase(String cpf);
}
