package grafica.pedidos.api.domain.funcionario.empregado.copiador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CopiadorRepository extends JpaRepository<Copiador, Long> {

    Page<Copiador> findByFuncionarioNomeIgnoreCase(String nomeCopiador, Pageable paginacao);

    Optional<Copiador> findByFuncionarioCpfOrFuncionarioNomeIgnoreCase(String cpf, String nome);
}
