package grafica.pedidos.api.service.gerente;

import grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas.GerenteVendas;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas.GerenteVendasRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas.GerenteVendasRepository;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteVendas.GerenteVendasResponse;
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
public class GerenteVendasService {

    @Autowired
    private GerenteVendasRepository gerenteVendasRepository;

    //Get
    public Page<GerenteVendasResponse> listarGerenteVendas(String nomeGerente, Pageable paginacao) {
        if(nomeGerente == null) {
            Page<GerenteVendas> gerentes = gerenteVendasRepository.findAll(paginacao);
            return GerenteVendasResponse.converter(gerentes);
        } else {
            Page<GerenteVendas> gerente = gerenteVendasRepository.findByFuncionarioNomeIgnoreCase(
                    nomeGerente, paginacao);
            return GerenteVendasResponse.converter(gerente);
        }
    }

    //Get id
    public ResponseEntity<GerenteVendasResponse> buscarGerenteVendas(Long id) {
        Optional<GerenteVendas> gerenteVendasOptional = gerenteVendasRepository.findById(id);
        if (gerenteVendasOptional.isPresent()) {
            return ResponseEntity.ok(GerenteVendasResponse.converterUmGerente(gerenteVendasOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    //cadastrar
    public ResponseEntity<GerenteVendasResponse> cadastrarGerenteVendas (
            GerenteVendasRegister gerenteVendasRegister,
            UriComponentsBuilder uriBuilder) throws Exception {

        Optional<GerenteVendas> gerenteVendasOptional = gerenteVendasRepository.findByFuncionarioCpfOrFuncionarioNomeIgnoreCase(
                gerenteVendasRegister.funcionarioRegister().cpf(), gerenteVendasRegister.funcionarioRegister().nome());

        if (gerenteVendasOptional.isEmpty()) {
            GerenteVendas gerente = gerenteVendasRegister.converter();
            gerenteVendasRepository.save(gerente);

            URI uri = uriBuilder.path("/funcionario/CGVendas/{id}").buildAndExpand(gerente.getId()).toUri();
            return ResponseEntity.created(uri).body(new GerenteVendasResponse(gerente));
        } else {
            throw new ItemJaExisteException("Gerente de Vendas j?? existe");
        }
    }

    //atualizar
    public ResponseEntity<GerenteVendasResponse> atualizarGerenteVendas(Long id,
                                                                        GerenteVendasRegister gerenteVendasRegister) {
        Optional<GerenteVendas> gerenteVendasOptional = gerenteVendasRepository.findById(id);
        if (gerenteVendasOptional.isPresent()) {

            GerenteVendas gerente = gerenteVendasOptional.get();
            gerente.getFuncionario().setCpf(gerenteVendasRegister.funcionarioRegister().cpf());
            gerente.getFuncionario().setNome(gerenteVendasRegister.funcionarioRegister().nome());
            gerente.getFuncionario().setTelefone(gerenteVendasRegister.funcionarioRegister().telefone());
            gerente.getFuncionario().setDataNascimento(gerenteVendasRegister.funcionarioRegister().dataNascimento());
            gerente.getFuncionario().setStatusTrabalho(gerenteVendasRegister.funcionarioRegister().statusTrabalho());

            gerenteVendasRepository.save(gerente);

            return ResponseEntity.ok(new GerenteVendasResponse(gerente));
        }
        return ResponseEntity.notFound().build();
    }

    //deletar
    public ResponseEntity<?> removerGerenteVendas(Long id) {
        Optional<GerenteVendas> gerenteVendasOptional = gerenteVendasRepository.findById(id);
        if (gerenteVendasOptional.isPresent()) {
            gerenteVendasRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
