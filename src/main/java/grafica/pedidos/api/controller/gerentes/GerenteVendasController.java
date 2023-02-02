package grafica.pedidos.api.controller.gerentes;

import grafica.pedidos.api.domain.funcionario.empregado.vendedor.VendedorRegister;
import grafica.pedidos.api.domain.funcionario.empregado.vendedor.VendedorResponse;
import grafica.pedidos.api.domain.pedido.PedidoResponse;
import grafica.pedidos.api.infra.exeption.ItemInesistenteException;
import grafica.pedidos.api.service.empregado.VendedorService;
import grafica.pedidos.api.service.pedido.PedidoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/funcionario/gerenteVendas")
public class GerenteVendasController {

//----------------------------------------------------------------------------------
//    Gerenciar Vendedor

    @Autowired
    private VendedorService vendedorService;

    @GetMapping
    public Page<VendedorResponse> listarVendedor(
            @RequestParam(required = false) String nomeVendedor,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                 Pageable paginacao) {
        return vendedorService.listarVendedor(nomeVendedor, paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<VendedorResponse> buscarVendedor(@PathVariable Long id) {
        return vendedorService.buscarVendedor(id);
    }

    @PostMapping
    public ResponseEntity<VendedorResponse> cadastrarVendedor(
            @RequestBody @Valid VendedorRegister vendedorRegister,
            UriComponentsBuilder uriBuilder) throws Exception {
        return vendedorService.cadastrarVendedor(vendedorRegister, uriBuilder);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<VendedorResponse> atualizarVendedor(
            @PathVariable Long id, @RequestBody @Valid VendedorRegister vendedorRegister) {
        return vendedorService.atualizarVendedor(id, vendedorRegister);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> deletarVendedor(@PathVariable Long id) {
        return vendedorService.removerVendedor(id);
    }

//-----------------------------------------------------------------------------------------------
//    Gerenciar Pedidos

    @Autowired
    private PedidoService pedidoService;

    @DeleteMapping("/deletarPedido/{id}")
    @Transactional
    public ResponseEntity<?> cancelarPedido(@PathVariable Long id) throws ItemInesistenteException {
        return pedidoService.cancelarPedido(id);
    }

    @GetMapping("/buscarPedidosFila")
    public Page<PedidoResponse> buscarFilaPedidos(
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10) Pageable paginacao) throws ItemInesistenteException {
        return pedidoService.buscarFilaPedidos(paginacao);
    }

}

