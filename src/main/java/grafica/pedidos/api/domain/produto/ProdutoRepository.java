package grafica.pedidos.api.domain.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Long> {

    @Query("SELECT u FROM Produto u WHERE u.name = :nomeProduto")
    Page<Produto> findByNameIgnoreCase(@Param("nomeProduto") String nomeProduto, Pageable paginacao);

    @Query("SELECT u FROM Produto u WHERE u.name = :name AND u.codigo=:codigo")
    Optional<Produto> findByNameAndCodigoIgnoreCase(@Param("name") String name,@Param("codigo") String codigo);
}
