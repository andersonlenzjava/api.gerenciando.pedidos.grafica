package grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao;

import grafica.pedidos.api.domain.funcionario.empregado.contador.EmpregadoContador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenteProducaoRepository extends JpaRepository<EmpregadoContador, Long> {
}
