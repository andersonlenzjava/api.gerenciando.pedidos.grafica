package grafica.pedidos.api.controller.empregado;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/funcionario/empregadoContador")
public class ContadorController {


    private ContadorService contadorService;

    @GetMapping
    public void retornarContador(@RequestParam(required = false) String nomeContador,
                                 @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                 Pageable paginacao) {
        return contadorService.retornarContador(nomeContador, paginacao);
    }

    @GetMapping("/{id}")
    public void listarContadores(@PathVariable Long id) {
        return contadorService.detalharContadorPorId(id);
    }

    @PostMapping
    public void cadastrarContador(@RequestBody @Valid ContadorRegister contadorRegister,
                                  UriComponentsBuilder uriBuilder) {
        return contadorService.cadastrarContador(contadorRegister, uriBuilder);
    }

    @PutMapping
    @Transactional
    public void atualizarContador(@PathVariable Long id, @RequestBody @Valid ContadorRegister contadorRegister) {
        return contadorService.atualizarContador(id, contadorRegister);
    }

    @DeleteMapping
    @Transactional
    public void deletarContador(@PathVariable Long id) {
        return contadorService.deletarContador(id);
    }

}
