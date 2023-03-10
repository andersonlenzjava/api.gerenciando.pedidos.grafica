package grafica.pedidos.api.controller.chefeGeral;

import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducaoRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducaoResponse;
import grafica.pedidos.api.service.gerente.GerenteProducaoService;
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
@RequestMapping("/funcionario/CGProducao")
public class CGProducaoController {

//-----------------------------------------------------------
//    Chefiar GerenteProducao

    @Autowired
    private GerenteProducaoService gereteProducaoService;

    @GetMapping
    public Page<GerenteProducaoResponse> listarGereteProducao(
            @RequestParam(required = false) String nomeGereteProducao,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                 Pageable paginacao) {
        return gereteProducaoService.listarGerenteProducao(nomeGereteProducao, paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GerenteProducaoResponse> buscarGereteProducao(@PathVariable Long id) {
        return gereteProducaoService.buscarGerenteProducao(id);
    }

    @PostMapping
    public ResponseEntity<GerenteProducaoResponse> cadastrarGereteProducao(
            @RequestBody @Valid GerenteProducaoRegister gereteProducaoRegister,
            UriComponentsBuilder uriBuilder) throws Exception {
        return gereteProducaoService.cadastrarGerenteProducao(gereteProducaoRegister, uriBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<GerenteProducaoResponse> atualizarGereteProducao(
            @PathVariable Long id, @RequestBody @Valid GerenteProducaoRegister gereteProducaoRegister) {
        return gereteProducaoService.atualizarGerenteProducao(id, gereteProducaoRegister);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarGereteProducao(@PathVariable Long id) {
        return gereteProducaoService.removerGerenteProducao(id);
    }

}
