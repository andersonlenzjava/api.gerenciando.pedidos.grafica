package grafica.pedidos.api.controller.empregado;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/funcionario/empregadoVendedor")
public class VendedorController {


    private VendedorService vendedorService;

    @GetMapping
    public void retornarVendedor(@RequestParam(required = false) String nomeVendedor,
                                 @PageableDefault(sort = "id", direction = Sort.Direction.ASC, page = 0, size = 10)
                                 Pageable paginacao) {
        return vendedorService.retornarContador(nomeVendedor, paginacao);
    }

    @GetMapping("/{id}")
    public void listarVendedores(@PathVariable Long id) {

        return contadorService.detalharVendedorPorId(id);
    }

    @PostMapping
    public void cadastrarVendedor(@RequestBody @Valid VendedorRegister vendedorRegister,
                                  UriComponentsBuilder uriBuilder) {
        return vendedorService.cadastrarVendedor(vendedorRegister, uriBuilder);
    }

    @PutMapping
    @Transactional
    public void atualizarVendedor(@PathVariable Long id, @RequestBody @Valid VendedorRegister vendedorRegister) {
        return vendedorService.atualizarVendedor(id, vendedorRegister);
    }

    @DeleteMapping
    @Transactional
    public void deletarVendedor(@PathVariable Long id) {

        return vendedorService.deletarVendedor(id);
    }

}
