package grafica.pedidos.api.controller.chefeGeral;

import grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas.GerenteVendasRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas.GerenteVendasResponse;
import grafica.pedidos.api.service.gerente.GerenteVendasService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/funcionario/CGVendas")
public class CGVendasController {

//-----------------------------------------------------------
//    Chefiar GerenteVendas

    private GerenteVendasService gerenteVendasService;

    @GetMapping
    public void listarGerenteVendas(@RequestParam(required = false) String nomeGerenteVendas,
                                 @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                 Pageable paginacao) {
        return gerenteVendasService.retornarGerenteVendas(nomeGerenteVendas, paginacao);
    }

    @GetMapping("/{id}")
    public void buscarGerenteVendas(@PathVariable Long id) {

        return gerenteVendasService.detalharGerenteVendasPorId(id);
    }

    @PostMapping
    public ResponseEntity<GerenteVendasResponse> cadastrarGerenteVendas(
            @RequestBody @Valid GerenteVendasRegister gerenteVendasRegister,
            UriComponentsBuilder uriBuilder) throws Exception {
        return gerenteVendasService.cadastrarGerenteVendas(gerenteVendasRegister, uriBuilder);
    }

    @PutMapping
    @Transactional
    public void atualizarGerenteVendas(@PathVariable Long id,
                                       @RequestBody @Valid GerenteVendasRegister gerenteVendasRegister) {
        return gerenteVendasService.atualizarGerenteVendas(id, gerenteVendasRegister);
    }

    @DeleteMapping
    @Transactional
    public void deletarGerenteVendas(@PathVariable Long id) {

        return gerenteVendasService.deletarGerenteVendas(id);
    }

}
