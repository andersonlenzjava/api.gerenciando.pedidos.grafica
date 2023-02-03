package grafica.pedidos.api.service.gerente;

import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinaceiroRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinanceiro;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinanceiroRepository;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro.GerenteFinanceiroResponse;
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
public class GerenteFinanceiroService {

    @Autowired
    private GerenteFinanceiroRepository gerenteFinanceiroRepository;

    //Get
    public Page<GerenteFinanceiroResponse> listarGerenteFinanceiro(String nomeGerente, Pageable paginacao) {
        if(nomeGerente == null) {
            Page<GerenteFinanceiro> gerentes = gerenteFinanceiroRepository.findAll(paginacao);
            return GerenteFinanceiroResponse.converter(gerentes);
        } else {
            Page<GerenteFinanceiro> gerente = gerenteFinanceiroRepository.findByFuncionarioNomeIgnoreCase(
                    nomeGerente, paginacao);
            return GerenteFinanceiroResponse.converter(gerente);
        }
    }

    //Get id
    public ResponseEntity<GerenteFinanceiroResponse> buscarGerenteFinanceiro(Long id) {
        Optional<GerenteFinanceiro> gerenteFinanceiroOptional = gerenteFinanceiroRepository.findById(id);
        if (gerenteFinanceiroOptional.isPresent()) {
            return ResponseEntity.ok(GerenteFinanceiroResponse.converterUmGerente(gerenteFinanceiroOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    //cadastrar
    public ResponseEntity<GerenteFinanceiroResponse> cadastrarGerenteFinanceiro(
            GerenteFinaceiroRegister gerenteFinaceiroRegister, UriComponentsBuilder uriBuilder) throws Exception {

        Optional<GerenteFinanceiro> gerenteFinanceiroOptional = gerenteFinanceiroRepository.
                findByFuncionarioCpfOrFuncionarioNomeIgnoreCase(
                gerenteFinaceiroRegister.funcionarioRegister().cpf(),
                        gerenteFinaceiroRegister.funcionarioRegister().nome());

        if (gerenteFinanceiroOptional.isEmpty()) {
            GerenteFinanceiro gerente = gerenteFinaceiroRegister.converter();
            gerenteFinanceiroRepository.save(gerente);

            URI uri = uriBuilder.path("/funcionario/CGFinaceiro/{id}").buildAndExpand(gerente.getId()).toUri();
            return ResponseEntity.created(uri).body(new GerenteFinanceiroResponse(gerente));
        } else {
            throw new ItemJaExisteException("Gerente Financeiro já existe");
        }
    }


    //atualizar
    public ResponseEntity<GerenteFinanceiroResponse> atualizarGerenteFinanceiro(
            Long id, GerenteFinaceiroRegister gerenteFinaceiroRegister) {
        Optional<GerenteFinanceiro> gerenteFinanceiroOptional = gerenteFinanceiroRepository.findById(id);
        if (gerenteFinanceiroOptional.isPresent()) {

            GerenteFinanceiro gerente = gerenteFinanceiroOptional.get();
            gerente.getFuncionario().setCpf(gerenteFinaceiroRegister.funcionarioRegister().cpf());
            gerente.getFuncionario().setNome(gerenteFinaceiroRegister.funcionarioRegister().nome());
            gerente.getFuncionario().setTelefone(gerenteFinaceiroRegister.funcionarioRegister().telefone());
            gerente.getFuncionario().setDataNascimento(gerenteFinaceiroRegister.funcionarioRegister().dataNascimento());
            gerente.getFuncionario().setStatusTrabalho(gerenteFinaceiroRegister.funcionarioRegister().statusTrabalho());

            gerenteFinanceiroRepository.save(gerente);

            return ResponseEntity.ok(new GerenteFinanceiroResponse(gerente));
        }
        return ResponseEntity.notFound().build();
    }

    //deletar
    public ResponseEntity<?> removerGerenteFinanceiro(Long id) {
        Optional<GerenteFinanceiro> gerenteFinanceiroOptional = gerenteFinanceiroRepository.findById(id);
        if (gerenteFinanceiroOptional.isPresent()) {
            gerenteFinanceiroRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
