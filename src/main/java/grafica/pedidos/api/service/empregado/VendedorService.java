package grafica.pedidos.api.service.empregado;

import grafica.pedidos.api.domain.funcionario.empregado.vendedor.Vendedor;
import grafica.pedidos.api.domain.funcionario.empregado.vendedor.VendedorRegister;
import grafica.pedidos.api.domain.funcionario.empregado.vendedor.VendedorRepository;
import grafica.pedidos.api.domain.funcionario.empregado.vendedor.VendedorResponse;
import grafica.pedidos.api.infra.exeption.ItemJaExisteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class VendedorService {

    @Autowired
    private VendedorRepository vendedorRepository;

    //Get
    public Page<VendedorResponse> listarVendedor(String nomeVendedor, Pageable paginacao) {
        if(nomeVendedor == null) {
            Page<Vendedor> vendedores = vendedorRepository.findAll(paginacao);
            return VendedorResponse.converter(vendedores);
        } else {
            Page<Vendedor> vendedor = vendedorRepository.findByFuncionarioNomeIgnoreCase(
                    nomeVendedor, paginacao);
            return VendedorResponse.converter(vendedor);
        }
    }

    //Get id
    public ResponseEntity<VendedorResponse> buscarVendedor(Long id) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(id);
        if (vendedorOptional.isPresent()) {
            return ResponseEntity.ok(VendedorResponse.converterUmVendedor(vendedorOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    //cadastrar
    public ResponseEntity<VendedorResponse> cadastrarVendedor(VendedorRegister vendedorRegister,
                                                              UriComponentsBuilder uriBuilder) throws Exception {

        Optional<Vendedor> vendedorOptional = vendedorRepository.findByFuncionarioCpfOrFuncionarioNomeIgnoreCase(
                vendedorRegister.funcionarioRegister().cpf(), vendedorRegister.funcionarioRegister().nome());

        if (vendedorOptional.isEmpty()) {
            Vendedor vendedor = vendedorRegister.converter();
            vendedorRepository.save(vendedor);

            URI uri = uriBuilder.path("/funcionario/gerenteVendas/{id}").buildAndExpand(vendedor.getId()).toUri();
            return ResponseEntity.created(uri).body(new VendedorResponse(vendedor));
        } else {
            throw new ItemJaExisteException("Vendedor j?? existe");
        }
    }


    //atualizar
    public ResponseEntity<VendedorResponse> atualizarVendedor(Long id, VendedorRegister vendedorRegister) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(id);
        if (vendedorOptional.isPresent()) {

            Vendedor vendedor = vendedorOptional.get();
            vendedor.getFuncionario().setCpf(vendedorRegister.funcionarioRegister().cpf());
            vendedor.getFuncionario().setNome(vendedorRegister.funcionarioRegister().nome());
            vendedor.getFuncionario().setTelefone(vendedorRegister.funcionarioRegister().telefone());
            vendedor.getFuncionario().setDataNascimento(vendedorRegister.funcionarioRegister().dataNascimento());
            vendedor.getFuncionario().setStatusTrabalho(vendedorRegister.funcionarioRegister().statusTrabalho());

            vendedorRepository.save(vendedor);

            return ResponseEntity.ok(new VendedorResponse(vendedor));
        }
        return ResponseEntity.notFound().build();
    }

    //deletar
    public ResponseEntity<?> removerVendedor(Long id) {
        Optional<Vendedor> vendedorOptional = vendedorRepository.findById(id);
        if (vendedorOptional.isPresent()) {
            vendedorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
