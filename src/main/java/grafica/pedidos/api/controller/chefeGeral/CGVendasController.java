package grafica.pedidos.api.controller.chefeGeral;

import grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas.GerenteVendasRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas.GerenteVendasResponse;
import grafica.pedidos.api.service.gerente.GerenteVendasService;
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
@RequestMapping("/funcionario/CGVendas")
public class CGVendasController {

//-----------------------------------------------------------
//    Chefiar GerenteVendas

    @Autowired
    private GerenteVendasService gerenteVendasService;

    @GetMapping
    public Page<GerenteVendasResponse> listarGerenteVendas(
            @RequestParam(required = false) String nomeGerenteVendas,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                 Pageable paginacao) {
        return gerenteVendasService.listarGerenteVendas(nomeGerenteVendas, paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GerenteVendasResponse> buscarGerenteVendas(@PathVariable Long id) {
        return gerenteVendasService.buscarGerenteVendas(id);
    }

    @PostMapping
    public ResponseEntity<GerenteVendasResponse> cadastrarGerenteVendas(
            @RequestBody @Valid GerenteVendasRegister gerenteVendasRegister,
            UriComponentsBuilder uriBuilder) throws Exception {
        return gerenteVendasService.cadastrarGerenteVendas(gerenteVendasRegister, uriBuilder);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<GerenteVendasResponse> atualizarGerenteVendas(
            @PathVariable Long id, @RequestBody @Valid GerenteVendasRegister gerenteVendasRegister) {
        return gerenteVendasService.atualizarGerenteVendas(id, gerenteVendasRegister);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> deletarGerenteVendas(@PathVariable Long id) {
        return gerenteVendasService.removerGerenteVendas(id);
    }

}
