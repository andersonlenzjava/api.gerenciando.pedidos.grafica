package grafica.pedidos.api.controller.chefeGeral;

import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinaceiroRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinanceiroResponse;
import grafica.pedidos.api.service.gerente.GerenteFinanceiroService;
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
@RequestMapping("/funcionario/CGFinaceiro")
public class CGFinaceiroController {

//-----------------------------------------------------------
//    Chefiar GerenteFinaceiro ..........

    @Autowired
    private GerenteFinanceiroService gerenteFinaceiroService;

    @GetMapping
    public Page<GerenteFinanceiroResponse> listarGerenteFinaceiro(
            @RequestParam(required = false) String nomeGerenteFinanceiro,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                 Pageable paginacao) {
        return gerenteFinaceiroService.listarGerenteFinanceiro(nomeGerenteFinanceiro, paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GerenteFinanceiroResponse> buscarGerenteFinaceiro(@PathVariable Long id) {
        return gerenteFinaceiroService.buscarGerenteFinanceiro(id);
    }

    @PostMapping
    public ResponseEntity<GerenteFinanceiroResponse> cadastrarGerenteFinaceiro(
            @RequestBody @Valid GerenteFinaceiroRegister gerenteFinaceiroRegister,
            UriComponentsBuilder uriBuilder) throws Exception {
        return gerenteFinaceiroService.cadastrarGerenteFinanceiro(gerenteFinaceiroRegister, uriBuilder);
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<GerenteFinanceiroResponse> atualizarGerenteFinaceiro(
            @PathVariable Long id, @RequestBody @Valid GerenteFinaceiroRegister gerenteFinaceiroRegister) {
        return gerenteFinaceiroService.atualizarGerenteFinanceiro(id, gerenteFinaceiroRegister);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarGerenteFinaceiro(@PathVariable Long id) {
        return gerenteFinaceiroService.removerGerenteFinanceiro(id);
    }

}
