package grafica.pedidos.api.domain.funcionario.chefeGeral;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChefeGeralRepository extends JpaRepository<ChefeGeral, Long> {
}
