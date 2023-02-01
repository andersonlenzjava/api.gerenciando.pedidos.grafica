package grafica.pedidos.api.domain.funcionario.empregado.copiador;

import grafica.pedidos.api.domain.funcionario.empregado.contador.EmpregadoContador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CopiadorRepository extends JpaRepository<EmpregadoContador, Long> {
}
