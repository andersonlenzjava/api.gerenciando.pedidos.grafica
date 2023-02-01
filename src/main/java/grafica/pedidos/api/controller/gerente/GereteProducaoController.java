package grafica.pedidos.api.controller.gerente;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/funcionario/gerenteProducao")
public class GereteProducaoController {


    private GereteProducaoService gereteProducaoService;

    @GetMapping
    public void retornarGereteProducao(@RequestParam(required = false) String nomeGereteProducao,
                                 @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                 Pageable paginacao) {
        return gereteProducaoService.retornarGereteProducao(nomeGereteProducao, paginacao);
    }

    @GetMapping("/{id}")
    public void listarGereteProducao(@PathVariable Long id) {

        return gereteProducaoService.detalharGereteProducaoPorId(id);
    }

    @PostMapping
    public void cadastrarGereteProducao(@RequestBody @Valid GereteProducaoRegister gereteProducaoRegister,
                                  UriComponentsBuilder uriBuilder) {
        return gereteProducaoService.cadastrarGereteProducao(gereteProducaoRegister, uriBuilder);
    }

    @PutMapping
    @Transactional
    public void atualizarGereteProducao(@PathVariable Long id,
                                        @RequestBody @Valid GereteProducaoRegister gereteProducaoRegister) {
        return gereteProducaoService.atualizarGereteProducao(id, gereteProducaoRegister);
    }

    @DeleteMapping
    @Transactional
    public void deletarGereteProducao(@PathVariable Long id) {

        return gereteProducaoService.deletarGereteProducao(id);
    }

}
