package grafica.pedidos.api.controller.chefeGeral;

import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinaceiroRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinanceiroResponse;
import grafica.pedidos.api.service.gerente.GerenteFinanceiroService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
//    Chefiar GerenteFinaceiro

    private GerenteFinanceiroService gerenteFinaceiroService;

    @GetMapping
    public void listarGerenteFinaceiro(@RequestParam(required = false) String nomeGerenteFinceiro,
                                 @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                 Pageable paginacao) {
        return gerenteFinaceiroService.retornarGerenteFinaceiro(nomeGerenteFinaceiro, paginacao);
    }

    @GetMapping("/{id}")
    public void listarGerenteFinaceiro(@PathVariable Long id) {
        return gerenteFinaceiroService.detalharGerenteFinaceiroPorId(id);
    }

    @PostMapping
    public ResponseEntity<GerenteFinanceiroResponse> cadastrarGerenteFinaceiro(
            @RequestBody @Valid GerenteFinaceiroRegister gerenteFinaceiroRegister,
            UriComponentsBuilder uriBuilder) throws Exception {
        return gerenteFinaceiroService.cadastrarGerenteFinanceiro(gerenteFinaceiroRegister, uriBuilder);
    }

    @PutMapping
    @Transactional
    public void atualizarGerenteFinaceiro(@PathVariable Long id,
                                          @RequestBody @Valid GerenteFinaceiroRegister gerenteFinaceiroRegister) {
        return gerenteFinaceiroService.atualizarGerenteFinaceiro(id, gerenteFinaceiroRegister);
    }

    @DeleteMapping
    @Transactional
    public void deletarGerenteFinaceiro(@PathVariable Long id) {
        return gerenteFinaceiroService.deletarGerenteFinaceiro(id);
    }

}
