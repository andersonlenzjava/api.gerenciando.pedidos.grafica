package grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GerenteFinanceiroRepository extends JpaRepository<GerenteFinanceiro, Long> {

    Page<GerenteFinanceiro> findByFuncionarioNomeIgnoreCase(String nomeGerente, Pageable paginacao);

    Optional<GerenteFinanceiro> findByFuncionarioCpfOrFuncionarioNomeIgnoreCase(String cpf, String nome);

    Optional<GerenteFinanceiro> findByFuncionarioCpfIgnoreCase(String cpfGerenteFinanceiro);
}
