package grafica.pedidos.api.domain.funcionario.empregado.contador;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContadorRepository extends JpaRepository<EmpregadoContador, Long> {
}
