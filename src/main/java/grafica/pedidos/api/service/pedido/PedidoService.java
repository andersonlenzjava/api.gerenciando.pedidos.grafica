package grafica.pedidos.api.service.pedido;

import grafica.pedidos.api.domain.funcionario.empregado.copiador.Copiador;
import grafica.pedidos.api.domain.funcionario.empregado.copiador.CopiadorRepository;
import grafica.pedidos.api.domain.funcionario.empregado.vendedor.Vendedor;
import grafica.pedidos.api.domain.funcionario.empregado.vendedor.VendedorRepository;
import grafica.pedidos.api.domain.pedido.Pedido;
import grafica.pedidos.api.domain.pedido.PedidoRegister;
import grafica.pedidos.api.domain.pedido.PedidoRepository;
import grafica.pedidos.api.domain.pedido.PedidoResponse;
import grafica.pedidos.api.domain.produto.Produto;
import grafica.pedidos.api.domain.produto.ProdutoRepository;
import grafica.pedidos.api.domain.statusPedido.StatusPedido;
import grafica.pedidos.api.infra.exeption.ItemInesistenteException;
import grafica.pedidos.api.infra.exeption.PedidoInalteravelException;
import grafica.pedidos.api.infra.exeption.ValorPagoInsuficienteException;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
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
            PedidoRegister pedidoRegister, UriComponentsBuilder uriBuilder) throws ItemInesistenteException {

        // inclus??o do copiador indefinido inicial
        Optional<Copiador> copiadorEstatico = copiadorRepository.findById(1L);

        if  (copiadorEstatico.isPresent()) {
            Pedido pedido = converterPedidoEntrada(pedidoRegister);

            pedido.setDataEmissao(LocalDateTime.now());
            pedido.setCopiador(copiadorEstatico.get());
            pedido.setStatusPedido(StatusPedido.ABERTO);

            pedidoRepository.save(pedido);

            URI uri = uriBuilder.path("/vendedor/{pedidoId}").buildAndExpand(pedido.getId()).toUri();
            return ResponseEntity.created(uri).body(new PedidoResponse(pedido));
        }
        throw new ItemInesistenteException("Copiador inicial inexistente!");
    }

    //atualizarDadosPedido
    public ResponseEntity<PedidoResponse> atualizarDadosPedido(
            Long pedidoId, PedidoRegister pedidoRegister, UriComponentsBuilder uriBuilder)
            throws PedidoInalteravelException, ItemInesistenteException {

        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);

        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();

            if ((pedido.getStatusPedido().equals(StatusPedido.ABERTO))
                    || (pedido.getStatusPedido().equals(StatusPedido.FILA))) {

                Optional<Produto> produtoOptional = produtoRepository.findById(pedidoRegister.produtoId());
                Optional<Vendedor> vendedorOptional = vendedorRepository.findById(pedidoRegister.vendedorId());

                if (produtoOptional.isPresent() && vendedorOptional.isPresent()) {

                    pedido.setNomeCliente(pedidoRegister.nomeCliente());
                    pedido.setProduto(produtoOptional.get());
                    pedido.setQuantidade(pedidoRegister.quantidade());
                    pedido.setVendedor(vendedorOptional.get());
                    pedido.calculaTotal();

                    pedidoRepository.save(pedido);

                    URI uri = uriBuilder.path("/vendedor/{pedidoId}").buildAndExpand(pedido.getId()).toUri();
                    return ResponseEntity.created(uri).body(new PedidoResponse(pedido));
                }
                throw new ItemInesistenteException("Produto ou pedido inesistente!");
            }
            throw new PedidoInalteravelException("Pedido n??o pode ser mais editado!");
        }
        throw new ItemInesistenteException("Pedido inexistente!");
    }

    //colocarFilaProducao
    public ResponseEntity<PedidoResponse> colocarFilaProducao(
            Long pedidoId,  UriComponentsBuilder uriBuilder)
            throws ItemInesistenteException, PedidoInalteravelException {

        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);

        if (pedidoOptional.isPresent()) {

            Pedido pedido = pedidoOptional.get();

            if (pedido.getStatusPedido().equals(StatusPedido.ABERTO)) {
                pedido.setStatusPedido(StatusPedido.FILA);

                pedidoRepository.save(pedido);
                URI uri = uriBuilder.path("/vendedor/{pedidoId}").buildAndExpand(pedido.getId()).toUri();
                return ResponseEntity.created(uri).body(new PedidoResponse(pedido));
            }
            throw new PedidoInalteravelException("Pedido n??o pode entrar em produ????o");
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
                pedido.setDataFinalizacao(LocalDateTime.now());
                pedido.setStatusPedido(StatusPedido.PAGOFINALIZADO);

                pedidoRepository.save(pedido);
                return ResponseEntity.ok(valorTroco);
            }
            throw new PedidoInalteravelException("Pedido n??o est?? impresso, ou j?? est?? pago");
        }
        throw new ItemInesistenteException("Pedido inexistente!");
    }

//    ---------------------------------------------------------------------
//    Copiador

    @Autowired
    private CopiadorRepository copiadorRepository;

    //tirarDaFilaProduzir
    public ResponseEntity<PedidoResponse> tirarFilaProduzir(Long pedidoId,
            UriComponentsBuilder uriBuilder)
            throws ItemInesistenteException {

        Optional<Copiador> optionalCopiador = copiadorRepository.findById(pedidoId);

        if (optionalCopiador.isPresent()) {

            List<Pedido> listaPedidosBanco = pedidoRepository.findByStatusPedidoFila(StatusPedido.FILA);

            Queue<Pedido> listaPedidos = new LinkedList<>(listaPedidosBanco);

            if (!listaPedidos.isEmpty()) {

                Pedido pedido = listaPedidos.remove();
                pedido.setCopiador(optionalCopiador.get());
                pedido.setStatusPedido(StatusPedido.PRODUZINDO);

                pedidoRepository.save(pedido);

                URI uri = uriBuilder.path("/copiador/{pedidoId}").buildAndExpand(pedido.getId()).toUri();
                return ResponseEntity.created(uri).body(new PedidoResponse(pedido));

            }
            throw new ItemInesistenteException("N??o h?? pedidos h?? imprimir");
        }
        throw new ItemInesistenteException("Copiador inesistente!");
    }

    //listarPedidosProduzindo
    public Page<PedidoResponse> listarPedidosProduzindo(String nomeProduto, Pageable paginacao) {
        if (nomeProduto == null) {
            Page<Pedido> pedidos = pedidoRepository.findByStatusPedido(StatusPedido.PRODUZINDO, paginacao);
            return PedidoResponse.converter(pedidos);
        } else {
            Page<Pedido> pedidos = pedidoRepository
                    .findByProdutoNameIgnoreCaseAndStatusPedido(nomeProduto, StatusPedido.PRODUZINDO, paginacao);
            return PedidoResponse.converter(pedidos);
        }
    }


    //fecharImpressao
    public ResponseEntity<PedidoResponse> fecharImpressao(Long pedidoId, UriComponentsBuilder uriBuilder)
            throws ItemInesistenteException {

        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);

        if(pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();

            if(pedido.getStatusPedido().equals(StatusPedido.PRODUZINDO)) {

                pedido.setStatusPedido(StatusPedido.IMPRESSO);
                pedidoRepository.save(pedido);

                URI uri = uriBuilder.path("/copiador/{pedidoId}").buildAndExpand(pedido.getId()).toUri();
                return ResponseEntity.created(uri).body(new PedidoResponse(pedido));
            }
            throw new ItemInesistenteException("Pedido n??o est?? em producao!");
        }
        throw new ItemInesistenteException("Pedido inesistente!");
    }

//    ---------------------------------------------------------------------
//    Contador

    //listarPedidosPagoFinalizado
    public Page<PedidoResponse> listarPedidosPagoFinalizado(String nomeProduto, Pageable paginacao) {
        if (nomeProduto == null) {
            Page<Pedido> pedidos = pedidoRepository.findByStatusPedido(StatusPedido.PAGOFINALIZADO, paginacao);
            return PedidoResponse.converter(pedidos);
        } else {
            Page<Pedido> pedidos = pedidoRepository
                    .findByProdutoNameIgnoreCaseAndStatusPedido(nomeProduto, StatusPedido.PAGOFINALIZADO, paginacao);
            return PedidoResponse.converter(pedidos);
        }
    }

    //listarPedidosRegistrado
    public Page<PedidoResponse> listarPedidosRegistrado(Pageable paginacao) throws ItemInesistenteException {
        Page<Pedido> pedidos = pedidoRepository.findByStatusPedido(StatusPedido.REGISTRADO, paginacao);
        if (!pedidos.isEmpty()) {
            return PedidoResponse.converter(pedidos);
        }
        throw new ItemInesistenteException("N??o h?? pedidos registrados");
    }

    //documentarPedido
    public ResponseEntity<PedidoResponse> registrarPedido(
            Long pedidoId,  UriComponentsBuilder uriBuilder)
            throws ItemInesistenteException {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(pedidoId);
        if (pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();
            if (pedido.getStatusPedido().equals(StatusPedido.PAGOFINALIZADO)) {

                pedido.setStatusPedido(StatusPedido.REGISTRADO);

                pedidoRepository.save(pedido);
                URI uri = uriBuilder.path("/contador/{pedidoId}").buildAndExpand(pedido.getId()).toUri();
                return ResponseEntity.created(uri).body(new PedidoResponse(pedido));
            }
            throw new ItemInesistenteException("Pedido n??o pode ser documentado");
        }
        throw new ItemInesistenteException("Pedido inexistente!");
    }

    //GetPorNomeCliente
    public Page<PedidoResponse> pedidosPorNomeCliente(
            String nomeCliente, Pageable paginacao) throws ItemInesistenteException {
        if (!(nomeCliente == null)) {
            Page<Pedido> pedidos = pedidoRepository.findByNomeClienteIgnoreCase(nomeCliente, paginacao);
            return PedidoResponse.converter(pedidos);
        }
        throw new ItemInesistenteException("Cliente inesistente!");
    }

    //GetPedidosMaioresQue
    public Page<PedidoResponse> pedidosMaioresQue(
            BigDecimal valorPedido, Pageable paginacao) throws ItemInesistenteException {
        if (!(valorPedido == null)) {
            Page<Pedido> pedidos = pedidoRepository.findMaiorQue(valorPedido, paginacao);
            return PedidoResponse.converter(pedidos);
        }
        throw new ItemInesistenteException("Valor n??o especificado!");
    }


    //GetListarPedidosPorProduto
    public Page<PedidoResponse> listarPedidosPorProduto(
            String nomeProduto, Pageable paginacao) throws ItemInesistenteException {
        if (!(nomeProduto == null)) {
            Page<Pedido> pedidos = pedidoRepository.findByProdutoNameIgnoreCase(nomeProduto, paginacao);
            return PedidoResponse.converter(pedidos);
        }
        throw new ItemInesistenteException("Cliente inesistente!");
    }

//    ---------------------------------------------
//    Gerente producao e vendas

    public ResponseEntity<?> cancelarPedido(Long id) throws ItemInesistenteException {
        Optional<Pedido> pedidoOptional = pedidoRepository.findById(id);
        if(pedidoOptional.isPresent()) {
            Pedido pedido = pedidoOptional.get();

            if ((pedido.getStatusPedido().equals(StatusPedido.ABERTO))
                    || (pedido.getStatusPedido().equals(StatusPedido.FILA))) {
                pedidoRepository.deleteById(id);
                return ResponseEntity.noContent().build();
            }
        }
        throw new ItemInesistenteException("Pedido inesistente!");
    }

    //buscarFilaPedidos
    public Page<PedidoResponse> buscarFilaPedidos(Pageable paginacao)
            throws ItemInesistenteException {

        Page<Pedido> listaPedidos = pedidoRepository.findByStatusPedido(StatusPedido.FILA, paginacao);
        if (!listaPedidos.isEmpty()) {

            return PedidoResponse.converter(listaPedidos);
        }
        throw new ItemInesistenteException("N??o h?? pedidos h?? imprimir");
    }

//    ---------------------------------------------------------------------
//    ConverterRegister AUX

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private VendedorRepository vendedorRepository;


    private Pedido converterPedidoEntrada(PedidoRegister pedidoRegister) throws ItemInesistenteException {

        Optional<Produto> produtoOptional = produtoRepository.findById(pedidoRegister.produtoId());
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(pedidoRegister.vendedorId());

        if (produtoOptional.isPresent() && vendedorOptional.isPresent()) {
            Pedido pedido = new Pedido(
                    pedidoRegister.nomeCliente(),
                    produtoOptional.get(),
                    pedidoRegister.quantidade(),
                    vendedorOptional.get());

            return pedido;

        }
        throw new ItemInesistenteException("Produto ou vendedor inesistente!!");
    }

}
