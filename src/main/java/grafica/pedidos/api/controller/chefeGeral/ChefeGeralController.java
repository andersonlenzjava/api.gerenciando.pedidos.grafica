package grafica.pedidos.api.controller.chefeGeral;

import grafica.pedidos.api.domain.funcionario.chefeGeral.ChefeGeralRegister;
import grafica.pedidos.api.domain.funcionario.chefeGeral.ChefeGeralResponse;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinaceiroRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinanceiroResponse;
import grafica.pedidos.api.service.gerente.ChefeGeralService;
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
@RequestMapping("/funcionario/chefeGeral")
public class ChefeGeralController {

//-----------------------------------------------------------
//    Chefiar ChefeGeral

    @Autowired
    private ChefeGeralService chefeGeralService;

    @GetMapping
    public Page<ChefeGeralResponse> listarChefeGeral(
            @RequestParam(required = false) String nomeChefeGeral,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                 Pageable paginacao) {
        return chefeGeralService.listarChefeGeral(nomeChefeGeral, paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChefeGeralResponse> buscarChefeGeral(@PathVariable Long id) {
        return chefeGeralService.buscarChefeGeral(id);
    }

    @PostMapping
    public ResponseEntity<ChefeGeralResponse> cadastrarChefeGeral(
            @RequestBody @Valid ChefeGeralRegister chefeGeralRegister,
            UriComponentsBuilder uriBuilder) throws Exception {
        return chefeGeralService.cadastrarChefeGeral(chefeGeralRegister, uriBuilder);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ChefeGeralResponse> atualizarChefeGeral(
            @PathVariable Long id, @RequestBody @Valid ChefeGeralRegister chefeGeralRegister) {
        return chefeGeralService.atualizarChefeGeral(id, chefeGeralRegister);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> deletarChefeGeral(@PathVariable Long id) {
        return chefeGeralService.removerChefeGeral(id);
    }

}
