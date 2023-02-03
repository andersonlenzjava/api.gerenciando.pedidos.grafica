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

    @Query("SELECT u FROM Produto u WHERE u.nome = :nome")
    Page<Produto> findByNameIgnoreCase(@Param("nome") String nomeProduto, Pageable paginacao);

    @Query("SELECT u FROM Produto u WHERE u.nome = :nome OR u.codigo=:codigo")
    Optional<Produto> findByNameAndCodigoIgnoreCase(@Param("nome") String nome, @Param("codigo") String codigo);
}
