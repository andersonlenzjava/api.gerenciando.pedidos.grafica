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
    private String nome;
    private String codigo;
    private BigDecimal valorProduto;
    private Boolean ativo = true;

    public  Produto(
            String nome,
            String codigo,
            BigDecimal valorProduto) {
        this.nome = nome;
        this.codigo = codigo;
        this.valorProduto = valorProduto;
    }

    public void ativar() {
        this.ativo = true;
    }

    public void desativar() {
        this.ativo = false;
    }
}
