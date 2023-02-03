package grafica.pedidos.api.domain.funcionario.empregado.contador;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ContadorRepository extends JpaRepository<Contador, Long> {

    Page<Contador> findByFuncionarioNomeIgnoreCase(String nomeContador, Pageable paginacao);

    Optional<Contador> findByFuncionarioCpfIgnoreCase(String cpf);

    Optional<Contador> findByFuncionarioCpfOrFuncionarioNomeIgnoreCase(String cpf, String nome);
}
