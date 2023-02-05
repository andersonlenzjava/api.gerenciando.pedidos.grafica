package grafica.pedidos.api.domain.funcionario.chefeGeral;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChefeGeralRepository extends JpaRepository<ChefeGeral, Long> {

    Page<ChefeGeral> findByFuncionarioNomeIgnoreCase(String nomeGerente, Pageable paginacao);

    Optional<ChefeGeral> findByFuncionarioCpfOrFuncionarioNomeIgnoreCase(String cpf, String nome);
}
