package grafica.pedidos.api.service.produto;

import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinanceiro;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinanceiroRepository;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducao;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducaoRepository;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas.GerenteVendas;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas.GerenteVendasRepository;
import grafica.pedidos.api.domain.produto.Produto;
import grafica.pedidos.api.domain.produto.ProdutoRegister;
import grafica.pedidos.api.domain.produto.ProdutoRepository;
import grafica.pedidos.api.domain.produto.ProdutoResponse;
import grafica.pedidos.api.infra.exeption.ItemInesistenteException;
import grafica.pedidos.api.infra.exeption.ItemJaExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private GerenteVendasRepository gerenteVendasRepository;

    @Autowired
    private GerenteFinanceiroRepository gerenteFinanceiroRepository;

    @Autowired
    private GerenteProducaoRepository gerenteProducaoRepository;

    //Get
    public Page<ProdutoResponse> listarProdutos(String nomeProduto, Pageable paginacao) {
        if (nomeProduto == null) {
            Page<Produto> produto = produtoRepository.findAll(paginacao);
            return ProdutoResponse.converter(produto);
        } else {
            Page<Produto> produto = produtoRepository.findByNameIgnoreCase(nomeProduto, paginacao);
            return ProdutoResponse.converter(produto);
        }
    }

    //Get id
    public ResponseEntity<ProdutoResponse> listarProdutosPorId(Long id) {
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isPresent()) {
            return ResponseEntity.ok(ProdutoResponse.converterUmProduto(produtoOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    //cadastrar
    public ResponseEntity<ProdutoResponse> cadastrarProduto(
            ProdutoRegister produtoRegister, UriComponentsBuilder uriBuilder) throws Exception {
        Optional<Produto> produtoOptional = produtoRepository.findByNameAndCodigoIgnoreCase(
                produtoRegister.name(), produtoRegister.codigo());

        if (!produtoOptional.isPresent()) {
            Optional<GerenteVendas> gerenteVendasOptional = gerenteVendasRepository.
                    findByFuncionarioCpfIgnoreCase(produtoRegister.cpfGerenteVendas());
            Optional<GerenteFinanceiro> gerenteFinanceiroOptional = gerenteFinanceiroRepository.
                    findByFuncionarioCpfIgnoreCase(produtoRegister.cpfGerenteFinanceiro());
            Optional<GerenteProducao> gerenteProducaoOptional = gerenteProducaoRepository.
                    findByFuncionarioCpfIgnoreCase(produtoRegister.cpfGerenteProducao());

            if ((gerenteVendasOptional.isPresent())
                    && (gerenteProducaoOptional.isPresent())
                    && (gerenteFinanceiroOptional.isPresent())) {
                Produto produto = produtoRegister.converter();

                produtoRepository.save(produto);
                URI uri = uriBuilder.path("/atualizarDados/{produtoId}").buildAndExpand(produto.getId()).toUri();
                return ResponseEntity.created(uri).body(new ProdutoResponse(produto));
            }
            throw new ItemInesistenteException("Gerente inexistente!");
        }
        throw new ItemJaExisteException("Produto já existe");
    }


    //atualizar
    public ResponseEntity<ProdutoResponse> atualizarDadosProduto(
            Long produtoId, ProdutoRegister produtoRegister, UriComponentsBuilder uriBuilder) throws Exception {
        Optional<Produto> produtoOptional = produtoRepository.findById(produtoId);

        if (produtoOptional.isPresent()) {
            Optional<GerenteVendas> gerenteVendasOptional = gerenteVendasRepository.
                    findByFuncionarioCpfIgnoreCase(produtoRegister.cpfGerenteVendas());
            Optional<GerenteFinanceiro> gerenteFinanceiroOptional = gerenteFinanceiroRepository.
                    findByFuncionarioCpfIgnoreCase(produtoRegister.cpfGerenteFinanceiro());
            Optional<GerenteProducao> gerenteProducaoOptional = gerenteProducaoRepository.
                    findByFuncionarioCpfIgnoreCase(produtoRegister.cpfGerenteProducao());

            if ((gerenteVendasOptional.isPresent())
                    && (gerenteProducaoOptional.isPresent())
                    && (gerenteFinanceiroOptional.isPresent())) {
                Produto produto = produtoRegister.converter();

                produtoRepository.save(produto);
                URI uri = uriBuilder.path("/atualizarDados/{produtoId}").buildAndExpand(produto.getId()).toUri();
                return ResponseEntity.created(uri).body(new ProdutoResponse(produto));
            }
            throw new ItemInesistenteException("Gerente inexistente!");
        }
        throw new ItemJaExisteException("Produto já existe");
    }

//    ----------------------------------------------------------
//    Ativar || Desativar

    //ativar
    public ResponseEntity<ProdutoResponse> ativarProduto(
            Long produtoId, ProdutoRegister produtoRegister, UriComponentsBuilder uriBuilder) throws Exception {
        Optional<Produto> produtoOptional = produtoRepository.findById(produtoId);

        if (produtoOptional.isPresent()) {
            Optional<GerenteVendas> gerenteVendasOptional = gerenteVendasRepository.
                    findByFuncionarioCpfIgnoreCase(produtoRegister.cpfGerenteVendas());
            Optional<GerenteFinanceiro> gerenteFinanceiroOptional = gerenteFinanceiroRepository.
                    findByFuncionarioCpfIgnoreCase(produtoRegister.cpfGerenteFinanceiro());
            Optional<GerenteProducao> gerenteProducaoOptional = gerenteProducaoRepository.
                    findByFuncionarioCpfIgnoreCase(produtoRegister.cpfGerenteProducao());

            if ((gerenteVendasOptional.isPresent())
                    && (gerenteProducaoOptional.isPresent())
                    && (gerenteFinanceiroOptional.isPresent())) {
                Produto produto = produtoRegister.converter();
                produto.ativar();

                produtoRepository.save(produto);
                URI uri = uriBuilder.path("/atualizarDados/{produtoId}").buildAndExpand(produto.getId()).toUri();
                return ResponseEntity.created(uri).body(new ProdutoResponse(produto));
            }
            throw new ItemInesistenteException("Gerente inexistente!");
        }
        throw new ItemInesistenteException("Produto inexistente!");
    }

    //desativar
    public ResponseEntity<ProdutoResponse> desativarProduto(
            Long produtoId, ProdutoRegister produtoRegister, UriComponentsBuilder uriBuilder) throws Exception {
        Optional<Produto> produtoOptional = produtoRepository.findById(produtoId);

        if (produtoOptional.isPresent()) {
            Optional<GerenteVendas> gerenteVendasOptional = gerenteVendasRepository.
                    findByFuncionarioCpfIgnoreCase(produtoRegister.cpfGerenteVendas());
            Optional<GerenteFinanceiro> gerenteFinanceiroOptional = gerenteFinanceiroRepository.
                    findByFuncionarioCpfIgnoreCase(produtoRegister.cpfGerenteFinanceiro());
            Optional<GerenteProducao> gerenteProducaoOptional = gerenteProducaoRepository.
                    findByFuncionarioCpfIgnoreCase(produtoRegister.cpfGerenteProducao());

            if ((gerenteVendasOptional.isPresent())
                    && (gerenteProducaoOptional.isPresent())
                    && (gerenteFinanceiroOptional.isPresent())) {
                Produto produto = produtoRegister.converter();
                produto.desativar();

                produtoRepository.save(produto);
                URI uri = uriBuilder.path("/atualizarDados/{produtoId}").buildAndExpand(produto.getId()).toUri();
                return ResponseEntity.created(uri).body(new ProdutoResponse(produto));
            }
            throw new ItemInesistenteException("Gerente inexistente!");
        }
        throw new ItemInesistenteException("Produto inexistente!");
    }

}