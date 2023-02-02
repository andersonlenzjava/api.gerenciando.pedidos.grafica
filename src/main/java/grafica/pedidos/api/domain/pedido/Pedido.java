package grafica.pedidos.api.domain.pedido;

import grafica.pedidos.api.domain.funcionario.empregado.copiador.Copiador;
import grafica.pedidos.api.domain.funcionario.empregado.vendedor.Vendedor;
import grafica.pedidos.api.domain.produto.Produto;
import grafica.pedidos.api.domain.statusPedido.StatusPedido;
import grafica.pedidos.api.infra.exeption.ValorPagoInsuficienteException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    @ManyToOne
    private Produto produto;

    private Double quantidade;

    private BigDecimal valorTotalServico = BigDecimal.ZERO;
    private BigDecimal troco = BigDecimal.ZERO;
    private BigDecimal valorPago = BigDecimal.ZERO;

    private LocalDateTime dataEmissao; // gerada automatico ao ser criado
    private LocalDateTime dataFinalizacao; // geraado automatico ao ser fechado
    private StatusPedido statusPedido; // gerado automático em outros pontos

    @ManyToOne
    private Vendedor vendedor;

    @ManyToOne
    private Copiador copiador; // atribuido ao chegar na produçao

    public Pedido (String nomeCliente,
                    Produto produto,
                    Double quantidade,
                    Vendedor vendedor) {
        this.nomeCliente = nomeCliente;
        this.produto = produto;
        this.quantidade = quantidade;
        this.vendedor = vendedor;
        this.valorTotalServico = produto.getValorProduto().multiply(BigDecimal.valueOf(quantidade));
    }

    public BigDecimal calcularTroco(BigDecimal valorPago) throws ValorPagoInsuficienteException {
        if (valorPago.compareTo(this.valorTotalServico) == -1) {
            this.troco = BigDecimal.ZERO;
            throw new ValorPagoInsuficienteException("O valor pago R$: " + valorPago + " é menor que o total do serviço R$: " + this.valorTotalServico);
        } else if (valorPago.compareTo(this.valorTotalServico) == 0) {
            this.troco = BigDecimal.ZERO;
            this.valorPago = valorPago;
            this.statusPedido = StatusPedido.PAGOFINALIZADO;
        } else if (valorPago.compareTo(this.valorTotalServico) == 1) {
            this.troco = valorPago.subtract(this.valorTotalServico);
            this.valorPago = valorPago;
            this.statusPedido = StatusPedido.PAGOFINALIZADO;
        }
        System.out.println(troco);
        return this.troco;
    }

}
