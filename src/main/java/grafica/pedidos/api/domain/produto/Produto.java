package grafica.pedidos.api.domain.produto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private BigDecimal valorProduto;
    private Boolean ativo = true;

    private void CadastrarProduto(
            String name,
            BigDecimal valorProduto) {
        this.name = name;
        this.valorProduto = valorProduto;
    }

}
