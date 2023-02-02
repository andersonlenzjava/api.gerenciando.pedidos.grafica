package grafica.pedidos.api.domain.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

    Page<Produto> findByNameIgnoreCase(String nomeProduto, Pageable paginacao);

    Optional<Produto> findByNameAndCodigoIgnoreCase(String name, String codigo);
}
