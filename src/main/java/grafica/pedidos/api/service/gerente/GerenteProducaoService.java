package grafica.pedidos.api.service.gerente;

import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinaceiroRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinanceiro;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducao;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducaoRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducaoRepository;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class GerenteProducaoService {

    @Autowired
    private GerenteProducaoRepository gerenteProducaoRepository;

    //Get
    public Page<GerenteProducaoResponse> listarGerenteProducao(String nomeGerente, Pageable paginacao) {
        if(nomeGerente == null) {
            Page<GerenteProducao> gerentes = gerenteProducaoRepository.findAll(paginacao);
            return GerenteProducaoResponse.converter(gerentes);
        } else {
            Page<GerenteProducao> gerente = gerenteProducaoRepository.findByFuncionarioNomeIgnoreCase(
                    nomeGerente, paginacao);
            return GerenteProducaoResponse.converterUmGerente(gerente);
        }
    }

    //Get id
    public ResponseEntity<GerenteProducaoResponse> buscarGerenteProducao(Long id) {
        Optional<GerenteProducao> gerenteProducaoOptional = gerenteProducaoRepository.findById(id);
        if (gerenteProducaoOptional.isPresent()) {
            return ResponseEntity.ok(GerenteProducaoResponse.converterUmGerente(gerente.get()));
        }
        return ResponseEntity.notFound().build();
    }

    //cadastrar
    public ResponseEntity<GerenteProducaoResponse> cadastrarGerenteProducao(
            GerenteFinaceiroRegister gerenteFinaceiroRegister,
            UriComponentsBuilder uriBuilder) throws Exception {

        Optional<GerenteProducao> gerenteProducaoOptional = gerenteProducaoRepository.findByFuncionarioCpfIgnoreCase(
                gerenteFinaceiroRegister.funcionarioRegister().cpf());

        if (gerenteProducaoOptional.isEmpty()) {
            GerenteProducao gerente = GerenteFinaceiroRegister.converter();
            gerenteProducaoRepository.save(gerente);

            URI uri = uriBuilder.path("/funcionario/CGProducao/{id}").buildAndExpand(gerente.getId()).toUri();
            return ResponseEntity.created(uri).body(new GerenteProducaoResponse(gerente));
        } else {
            throw new ItemJaExisteException("Vendedor já existe");
        }
    }


    //atualizar
    public ResponseEntity<GerenteProducaoResponse> atualizarGerenteProducao(Long id, GerenteProducaoRegister gerenteProducaoRegister) {
        Optional<GerenteFinanceiro> gerenteProducaoOptional = gerenteProducaoRepository.findById(id);
        if (gerenteProducaoOptional.isPresent()) {

            GerenteProducao gerente = gerenteProducaoOptional.get();
            gerente.getFuncionario().setCpf(gerenteProducaoRegister.funcionarioRegister().cpf());
            gerente.getFuncionario().setNome(gerenteProducaoRegister.funcionarioRegister().nome());
            gerente.getFuncionario().setTelefone(gerenteProducaoRegister.funcionarioRegister().telefone());
            gerente.getFuncionario().setDataNascimento(gerenteProducaoRegister.funcionarioRegister().dataNascimento());
            gerente.getFuncionario().setStatusTrabalho(gerenteProducaoRegister.funcionarioRegister().statusTrabalho());

            gerenteProducaoRepository.save(gerente);

            return ResponseEntity.ok(new GerenteProducaoResponse(gerente));
        }
        return ResponseEntity.notFound().build();
    }

    //deletar
    public ResponseEntity<?> removerGerenteProducao(Long id) {
        Optional<GerenteProducao> gerenteProducaoOptional = gerenteProducaoRepository.findById(id);
        if (gerenteProducaoOptional.isPresent()) {
            gerenteProducaoOptional.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

}
