package grafica.pedidos.api.service.gerente;

import grafica.pedidos.api.domain.funcionario.chefeGeral.ChefeGeral;
import grafica.pedidos.api.domain.funcionario.chefeGeral.ChefeGeralRegister;
import grafica.pedidos.api.domain.funcionario.chefeGeral.ChefeGeralRepository;
import grafica.pedidos.api.domain.funcionario.chefeGeral.ChefeGeralResponse;
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
public class ChefeGeralService {

    @Autowired
    private ChefeGeralRepository chefeGeralRepository;

    //Get
    public Page<ChefeGeralResponse> listarChefeGeral(String nomeChefeGeral, Pageable paginacao) {
        if(nomeChefeGeral == null) {
            Page<ChefeGeral> chefes = chefeGeralRepository.findAll(paginacao);
            return ChefeGeralResponse.converter(chefes);
        } else {
            Page<ChefeGeral> chefe = chefeGeralRepository.findByFuncionarioNomeIgnoreCase(
                    nomeChefeGeral, paginacao);
            return ChefeGeralResponse.converter(chefe);
        }
    }

    //Get id
    public ResponseEntity<ChefeGeralResponse> buscarChefeGeral(Long id) {
        Optional<ChefeGeral> chefeGeralOptional = chefeGeralRepository.findById(id);
        if (chefeGeralOptional.isPresent()) {
            return ResponseEntity.ok(ChefeGeralResponse.converterUmChefeGeral(chefeGeralOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    //cadastrar
    public ResponseEntity<ChefeGeralResponse> cadastrarChefeGeral(
            ChefeGeralRegister chefeGeralRegister, UriComponentsBuilder uriBuilder) throws Exception {

        Optional<ChefeGeral> chefeGeralOptional = chefeGeralRepository.findByFuncionarioCpfIgnoreCase(
                chefeGeralRegister.funcionarioRegister().cpf());

        if (chefeGeralOptional.isEmpty()) {
            ChefeGeral chefeGeral = chefeGeralRegister.converter();
            chefeGeralRepository.save(chefeGeral);

            URI uri = uriBuilder.path("/funcionario/chefeGeral/{id}").buildAndExpand(chefeGeral.getId()).toUri();
            return ResponseEntity.created(uri).body(new ChefeGeralResponse(chefeGeral));
        } else {
            throw new ItemJaExisteException("Chefe geral já existe!!");
        }
    }

    //atualizar
    public ResponseEntity<ChefeGeralResponse> atualizarChefeGeral(Long id, ChefeGeralRegister chefeGeralRegister) {
        Optional<ChefeGeral> chefeGeralOptional = chefeGeralRepository.findById(id);
        if (chefeGeralOptional.isPresent()) {

            ChefeGeral chefeGeral = chefeGeralOptional.get();
            chefeGeral.getFuncionario().setCpf(chefeGeralRegister.funcionarioRegister().cpf());
            chefeGeral.getFuncionario().setNome(chefeGeralRegister.funcionarioRegister().nome());
            chefeGeral.getFuncionario().setTelefone(chefeGeralRegister.funcionarioRegister().telefone());
            chefeGeral.getFuncionario().setDataNascimento(chefeGeralRegister.funcionarioRegister().dataNascimento());
            chefeGeral.getFuncionario().setStatusTrabalho(chefeGeralRegister.funcionarioRegister().statusTrabalho());

            chefeGeralRepository.save(chefeGeral);

            return ResponseEntity.ok(new ChefeGeralResponse(chefeGeral));
        }
        return ResponseEntity.notFound().build();
    }

    //deletar
    public ResponseEntity<?> removerChefeGeral(Long id) {
        Optional<ChefeGeral> chefeGeralOptional = chefeGeralRepository.findById(id);
        if (chefeGeralOptional.isPresent()) {
            chefeGeralRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
