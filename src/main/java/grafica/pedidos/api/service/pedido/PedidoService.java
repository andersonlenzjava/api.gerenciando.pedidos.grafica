package grafica.pedidos.api.service.pedido;

import grafica.pedidos.api.domain.pedido.Pedido;
import grafica.pedidos.api.domain.pedido.PedidoRegister;
import grafica.pedidos.api.domain.pedido.PedidoRepository;
import grafica.pedidos.api.domain.pedido.PedidoResponse;
import grafica.pedidos.api.domain.statusPedido.StatusPedido;
import grafica.pedidos.api.infra.exeption.ItemInesistenteException;
import grafica.pedidos.api.infra.exeption.PedidoInalteravelException;
import grafica.pedidos.api.infra.exeption.ValorPagoInsuficienteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Queue;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

//    ---------------------------------------------------------------------
//    Vendedor

    //Get
    public Page<PedidoResponse> listarPedidos(String nomeCliente, Pageable paginacao) {
        if (nomeCliente == null) {
            Page<Pedido> pedidos = pedidoRepository.findAll(paginacao);
            return PedidoResponse.converter(pedidos);
        } else {
            Page<Pedido> pedidos = pedidoRepository.findByNomeClienteIgnoreCase(nomeCliente, paginacao);
            return PedidoResponse.converter(pedidos);
        }
    }

    //Get id
    public ResponseEntity<PedidoResponse> listarPedidoPorId(Long id) {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if (pedidoOptional.isPresent()) {
            return ResponseEntity.ok(PedidoResponse.converterUmProduto(pedidoOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    //abrirPedido
    public ResponseEntity<PedidoResponse> abrirPedido(
            PedidoRegister pedidoRegister, UriComponentsBuilder uriBuilder) {

        Pedido pedido = pedidoRegister.converter();

        pedido.setDataEmissao(LocalDateTime.now());
        pedido.setStatusPedido(StatusPedido.ABERTO);

        pedidoRepository.save(pedido);

        URI uri = uriBuilder.path("/vendedor/{pedidoId}").buildAndExpand(pedido.getId()).toUri();
        return ResponseEntity.created(uri).body(new PedidoResponse(pedido));
    }

    //atualizarDadosPedido
    public ResponseEntity<PedidoResponse> atualizarDadosPedido(
            Long pedidoId, PedidoRegister pedidoRegister, UriComponentsBuilder uriBuilder)
            throws PedidoInalteravelException, ItemInesistenteException {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoRegister.converter();
            if ((pedido.getStatusPedido().equals(StatusPedido.ABERTO))
                    || (pedido.getStatusPedido().equals(StatusPedido.FILA))) {

                pedidoRepository.save(pedido);
                URI uri = uriBuilder.path("/vendedor/{pedidoId}").buildAndExpand(pedido.getId()).toUri();
                return ResponseEntity.created(uri).body(new PedidoResponse(pedido));
            }
            throw new PedidoInalteravelException("Pedido não pode ser mais editado");
        }
        throw new ItemInesistenteException("Pedido inexistente!");
    }

    //colocarFilaProducao
    public ResponseEntity<PedidoResponse> colocarFilaProducao(
            Long pedidoId, PedidoRegister pedidoRegister, UriComponentsBuilder uriBuilder)
            throws ItemInesistenteException {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoRegister.converter();
            if (pedido.getStatusPedido().equals(StatusPedido.ABERTO)) {

                pedido.setStatusPedido(StatusPedido.FILA);

                pedidoRepository.save(pedido);
                URI uri = uriBuilder.path("/vendedor/{pedidoId}").buildAndExpand(pedido.getId()).toUri();
                return ResponseEntity.created(uri).body(new PedidoResponse(pedido));
            }
            throw new ItemInesistenteException("Pedido não pode entrar em produção");
        }
        throw new ItemInesistenteException("Pedido inexistente!");
    }

    //calculaTrocoFechaPedido
    public ResponseEntity<BigDecimal> calculaTrocoFechaPedido(
            Long pedidoId, BigDecimal valorPago)
            throws PedidoInalteravelException, ItemInesistenteException, ValorPagoInsuficienteException {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            if (pedido.getStatusPedido().equals(StatusPedido.IMPRESSO)) {

                BigDecimal valorTroco = pedido.calcularTroco(valorPago);
                pedido.setStatusPedido(StatusPedido.PAGOFINALIZADO);

                pedidoRepository.save(pedido);
                return ResponseEntity.ok(valorTroco);
            }
            throw new PedidoInalteravelException("Pedido não está impresso, ou já está pago");
        }
        throw new ItemInesistenteException("Pedido inexistente!");
    }

//    ---------------------------------------------------------------------
//    Copiador

    //tirarDaFilaProduzir
    public ResponseEntity<PedidoResponse> tirarFilaProduzir(
            UriComponentsBuilder uriBuilder)
            throws ItemInesistenteException {

        Queue<Pedido> listaPedidos = pedidoRepository.findByStatusPedidoFila(StatusPedido.FILA);
        if (!listaPedidos.isEmpty()) {

           Pedido pedido = listaPedidos.remove();
           pedido.setStatusPedido(StatusPedido.PRODUZINDO);

            pedidoRepository.save(pedido);

            URI uri = uriBuilder.path("/vendedor/{pedidoId}").buildAndExpand(pedido.getId()).toUri();
                return ResponseEntity.created(uri).body(new PedidoResponse(pedido));
            }
        throw new ItemInesistenteException("Não hé pedidos há imprimir");
    }

    //fecharImpressao
    public ResponseEntity<PedidoResponse> fecharImpressao(
            Long pedidoId, UriComponentsBuilder uriBuilder)
            throws ItemInesistenteException {

        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);

        if(pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();

            if(pedido.getStatusPedido().equals(StatusPedido.PRODUZINDO)) {

                pedido.setStatusPedido(StatusPedido.IMPRESSO);
                pedidoRepository.save(pedido);

                URI uri = uriBuilder.path("/vendedor/{pedidoId}").buildAndExpand(pedido.getId()).toUri();
                return ResponseEntity.created(uri).body(new PedidoResponse(pedido));
            }
            throw new ItemInesistenteException("Pedido não está em producao!");
        }
        throw new ItemInesistenteException("Pedido inesistente!");
    }

}
