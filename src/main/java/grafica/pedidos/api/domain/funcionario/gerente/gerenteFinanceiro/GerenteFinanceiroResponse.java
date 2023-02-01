package grafica.pedidos.api.domain.funcionario.gerente.gerenteFinanceiro;

import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducao;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducaoRegister;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducaoRepository;
import grafica.pedidos.api.domain.funcionario.gerente.gerenteProducao.GerenteProducaoResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

public record GerenteFinanceiroResponse() {

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
            return GerenteFinanceiroResponse.converterUmGerente(gerente);
        }
    }

    //Get id
    public ResponseEntity<GerenteFinanceiroResponse> buscarGerenteFinanceiro(Long id) {
        Optional<GerenteFinanceiro> gerenteFinanceiroOptional = gerenteFinanceiroRepository.findById(id);
        if (gerenteFinanceiroOptional.isPresent()) {
            return ResponseEntity.ok(GerenteFinanceiroResponse.converterUmGerente(gerente.get()));
        }
        return ResponseEntity.notFound().build();
    }

    //cadastrar
    public ResponseEntity<GerenteFinanceiroResponse> cadastrarGerenteFinanceiro(
            GerenteFinaceiroRegister gerenteFinaceiroRegister,
            UriComponentsBuilder uriBuilder) throws Exception {

        Optional<GerenteFinanceiro> gerenteFinanceiroOptional = gerenteFinanceiroRepository.findByFuncionarioCpfIgnoreCase(
                gerenteFinaceiroRegister.funcionarioRegister().cpf());

        if (gerenteFinanceiroOptional.isEmpty()) {
            GerenteProducao gerente = GerenteFinaceiroRegister.converter();
            gerenteFinanceiroRepository.save(gerente);

            URI uri = uriBuilder.path("/funcionario/CGFinaceiro/{id}").buildAndExpand(gerente.getId()).toUri();
            return ResponseEntity.created(uri).body(new GerenteFinanceiroResponse(gerente));
        } else {
            throw new ItemJaExisteException("Gerente já existe");
        }
    }


    //atualizar
    public ResponseEntity<GerenteFinanceiroResponse> atualizarGerenteFinanceiro(Long id, GerenteProducaoRegister gerenteProducaoRegister) {
        Optional<GerenteFinanceiro> gerenteFinanceiroOptional = gerenteFinanceiroRepository.findById(id);
        if (gerenteFinanceiroOptional.isPresent()) {

            GerenteProducao gerente = gerenteFinanceiroOptional.get();
            gerente.getFuncionario().setCpf(gerenteProducaoRegister.funcionarioRegister().cpf());
            gerente.getFuncionario().setNome(gerenteProducaoRegister.funcionarioRegister().nome());
            gerente.getFuncionario().setTelefone(gerenteProducaoRegister.funcionarioRegister().telefone());
            gerente.getFuncionario().setDataNascimento(gerenteProducaoRegister.funcionarioRegister().dataNascimento());
            gerente.getFuncionario().setStatusTrabalho(gerenteProducaoRegister.funcionarioRegister().statusTrabalho());

            gerenteFinanceiroRepository.save(gerente);

            return ResponseEntity.ok(new GerenteFinanceiroResponse(gerente));
        }
        return ResponseEntity.notFound().build();
    }

    //deletar
    public ResponseEntity<?> removerGerenteFinanceiro(Long id) {
        Optional<GerenteProducao> gerenteFinanceiroOptional = gerenteFinanceiroRepository.findById(id);
        if (gerenteFinanceiroOptional.isPresent()) {
            gerenteFinanceiroOptional.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
