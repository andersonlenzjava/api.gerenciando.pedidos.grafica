package grafica.pedidos.api.domain.funcionario.empregado.vendedor;

import grafica.pedidos.api.domain.funcionario.empregado.contador.EmpregadoContador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendedorRepository extends JpaRepository<EmpregadoContador, Long> {
}
