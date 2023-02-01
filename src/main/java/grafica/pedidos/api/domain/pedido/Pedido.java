package grafica.pedidos.api.domain.pedido;

import grafica.pedidos.api.domain.funcionario.empregado.copiador.Copiador;
import grafica.pedidos.api.domain.funcionario.empregado.vendedor.Vendedor;
import grafica.pedidos.api.domain.produto.Produto;
import grafica.pedidos.api.domain.statusPedido.StatusPedido;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeCliente;
    private Produto produto;
    private Double quantidade;
    private LocalDateTime dataEmissao; // gerada automatico ao ser criado
    private LocalDateTime dataFinalizacao; // geraado automatico ao ser fechado
    private StatusPedido statusPedido; // gerado automático em outros pontos
    private Vendedor vendedor;
    private Copiador produtorCopias; // atribuido ao chegar na produçao


}
