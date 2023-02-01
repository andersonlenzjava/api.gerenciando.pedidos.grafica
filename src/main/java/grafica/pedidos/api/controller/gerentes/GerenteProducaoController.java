package grafica.pedidos.api.controller.gerentes;

import grafica.pedidos.api.domain.funcionario.empregado.copiador.CopiadorRegister;
import grafica.pedidos.api.service.empregado.CopiadorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/funcionario/gerenteProducao")
public class GerenteProducaoController {

//----------------------------------------------------------------------------------
//    Gerenciar Copiador

    private CopiadorService copiadorService;

    @GetMapping
    public void listarCopiador(@RequestParam(required = false) String nomeCopiador,
                                 @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                 Pageable paginacao) {
        return copiadorService.retornarCopiador(nomeCopiador, paginacao);
    }

    @GetMapping("/{id}")
    public void buscarCopiador(@PathVariable Long id) {
        return copiadorService.detalharCopiadorPorId(id);
    }

    @PostMapping
    public void cadastrarCopiador(@RequestBody @Valid CopiadorRegister copiadorRegister,
                                  UriComponentsBuilder uriBuilder) {
        return copiadorService.cadastrarCopiador(copiadorRegister, uriBuilder);
    }

    @PutMapping
    @Transactional
    public void atualizarCopiador(@PathVariable Long id,
                                        @RequestBody @Valid CopiadorRegister copiadorRegister) {
        return copiadorService.atualizarCopiador(id, copiadorRegister);
    }

    @DeleteMapping
    @Transactional
    public void deletarProdutorCopias(@PathVariable Long id) {

        return copiadorService.deletarCopiador(id);
    }

//-----------------------------------------------------------------------------------------------
//    Gerenciar Pedidos

    @DeleteMapping
    @Transactional
    public void cancelarPedido(@PathVariable Long id) {

        return vendedorService.deletarVendedor(id);
    }

    @GetMapping
    public void buscarFilaPedidos(@RequestParam(required = false) String nomeCopiador,
                               @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                               Pageable paginacao) {
        return copiadorService.retornarCopiador(nomeCopiador, paginacao);
    }

}
