package grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GerenteProducaoRepository extends JpaRepository<GerenteProducao, Long> {

    Page<GerenteProducao> findByFuncionarioNomeIgnoreCase(String nomeGerente, Pageable paginacao);

    Optional<GerenteProducao> findByFuncionarioCpfIgnoreCase(String cpf);
}
