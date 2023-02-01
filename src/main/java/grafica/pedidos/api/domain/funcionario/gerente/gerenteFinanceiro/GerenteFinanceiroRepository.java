package grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro;

import grafica.pedidos.api.domain.funcionario.empregado.contador.EmpregadoContador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GerenteFinanceiroRepository extends JpaRepository<EmpregadoContador, Long> {
}
