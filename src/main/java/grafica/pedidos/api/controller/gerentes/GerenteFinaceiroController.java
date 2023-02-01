package grafica.pedidos.api.controller.gerentes;

import grafica.pedidos.api.domain.funcionario.empregado.contador.ContadorRegister;
import grafica.pedidos.api.domain.funcionario.empregado.contador.ContadorResponse;
import grafica.pedidos.api.service.empregado.ContadorService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/funcionario/gerenteFinaceiro")
public class GerenteFinaceiroController {

//----------------------------------------------------------------------------------
//    Gerenciar Contador

    private ContadorService contadorService;

    @GetMapping
    public Page<ContadorResponse> listarContador(
            @RequestParam(required = false) String nomeContador,
            @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                 Pageable paginacao) {
        return contadorService.listarContador(nomeContador, paginacao);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContadorResponse> buscarContador(@PathVariable Long id) {
        return contadorService.buscarContador(id);
    }

    @PostMapping
    public ResponseEntity<ContadorResponse> cadastrarContador(
            @RequestBody @Valid ContadorRegister contadorRegister,
            UriComponentsBuilder uriBuilder) throws Exception {
        return contadorService.cadastrarContador(contadorRegister, uriBuilder);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<ContadorResponse> atualizarContador(
            @PathVariable Long id,
            @RequestBody @Valid ContadorRegister contadorRegister) {
        return contadorService.atualizarContador(id, contadorRegister);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity<?> deletarContador(@PathVariable Long id) {
        return contadorService.removerContador(id);
    }

}
