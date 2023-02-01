package grafica.pedidos.api.controller.empregado;

import grafica.pedidos.api.domain.funcionario.empregado.produtorCopias.ProdutorCopias;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/funcionario/empregadoProdutorCopias")
public class ProdutorCopiasController {


    private ProdutorCopiasService produtorCopiasService;

    @GetMapping
    public void retornarProdutorCopias(@RequestParam(required = false) String nomeProdutorCopias,
                                 @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                 Pageable paginacao) {
        return produtorCopiasService.retornarProdutorCopias(nomeProdutorCopias, paginacao);
    }

    @GetMapping("/{id}")
    public void listarProdutoresCopias(@PathVariable Long id) {
        return produtorCopiasService.detalharProdutorCopiasPorId(id);
    }

    @PostMapping
    public void cadastrarProdutorCopias(@RequestBody @Valid ProdutorCopiasRegister produtorCopiasRegister,
                                  UriComponentsBuilder uriBuilder) {
        return produtorCopiasService.cadastrarProdutorCopias(produtorCopiasRegister, uriBuilder);
    }

    @PutMapping
    @Transactional
    public void atualizarProdutorCopias(@PathVariable Long id,
                                        @RequestBody @Valid produtorCopiasRegister produtorCopiasRegister) {
        return produtorCopiasService.atualizarProdutorCopias(id, produtorCopiasRegister);
    }

    @DeleteMapping
    @Transactional
    public void deletarProdutorCopias(@PathVariable Long id) {

        return produtorCopiasService.deletarProdutorCopias(id);
    }

}
