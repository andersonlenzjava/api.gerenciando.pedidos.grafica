package grafica.pedidos.api.service.empregado;

import grafica.pedidos.api.domain.funcionario.empregado.copiador.Copiador;
import grafica.pedidos.api.domain.funcionario.empregado.copiador.CopiadorRegister;
import grafica.pedidos.api.domain.funcionario.empregado.copiador.CopiadorRepository;
import grafica.pedidos.api.domain.funcionario.empregado.copiador.CopiadorResponse;
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
public class CopiadorService {

    @Autowired
    private CopiadorRepository copiadorRepository;

    //Get
    public Page<CopiadorResponse> listarCopiador(String nomeCopiador, Pageable paginacao) {
        if(nomeCopiador == null) {
            Page<Copiador> copiadores = copiadorRepository.findAll(paginacao);
            return CopiadorResponse.converter(copiadores);
        } else {
            Page<Copiador> copiador = copiadorRepository.findByFuncionarioNomeIgnoreCase(
                    nomeCopiador, paginacao);
            return CopiadorResponse.converter(copiador);
        }
    }

    //Get id
    public ResponseEntity<CopiadorResponse> buscarCopiador(Long id) {
        Optional<Copiador> copiadorOptional = copiadorRepository.findById(id);
        if (copiadorOptional.isPresent()) {
            return ResponseEntity.ok(CopiadorResponse.converterUmCopiador(copiadorOptional.get()));
        }
        return ResponseEntity.notFound().build();
    }

    //cadastrar
    public ResponseEntity<CopiadorResponse> cadastrarCopiador(CopiadorRegister copiadorRegister,
                                                               UriComponentsBuilder uriBuilder) throws Exception {

        // para que o primeiro copiador seja o do sistema, para não gerar problemas com null
        cadastrarcopiadorSistema();

        Optional<Copiador> copiadorOptional = copiadorRepository.findByFuncionarioCpfOrFuncionarioNomeIgnoreCase(
                copiadorRegister.funcionarioRegister().cpf(), copiadorRegister.funcionarioRegister().nome());

        if (copiadorOptional.isEmpty()) {
            Copiador copiador = copiadorRegister.converter();
            copiadorRepository.save(copiador);

            URI uri = uriBuilder.path("/funcionario/gerenteProducao/{id}").buildAndExpand(copiador.getId()).toUri();
            return ResponseEntity.created(uri).body(new CopiadorResponse(copiador));
        } else {
            throw new ItemJaExisteException("Copiador já existe");
        }
    }

    private Boolean cadastrarcopiadorSistema() {
        Optional<Copiador> copiadorOptional = copiadorRepository.findById(1L);
        if (copiadorOptional.isPresent()) {
            return true;
        } else {
            Copiador copiador = new Copiador("11111111111", "Copiador sistema", "11/11/1111", "111111111");
        copiadorRepository.save(copiador);
            return true;
        }
    }


    //atualizar
    public ResponseEntity<CopiadorResponse> atualizarCopiador(Long id, CopiadorRegister copiadorRegister) {
        Optional<Copiador> copiadorOptional = copiadorRepository.findById(id);
        if (copiadorOptional.isPresent()) {

            Copiador copiador = copiadorOptional.get();
            copiador.getFuncionario().setCpf(copiadorRegister.funcionarioRegister().cpf());
            copiador.getFuncionario().setNome(copiadorRegister.funcionarioRegister().nome());
            copiador.getFuncionario().setTelefone(copiadorRegister.funcionarioRegister().telefone());
            copiador.getFuncionario().setDataNascimento(copiadorRegister.funcionarioRegister().dataNascimento());
            copiador.getFuncionario().setStatusTrabalho(copiadorRegister.funcionarioRegister().statusTrabalho());

            copiadorRepository.save(copiador);

            return ResponseEntity.ok(new CopiadorResponse(copiador));
        }
        return ResponseEntity.notFound().build();
    }

    //deletar
    public ResponseEntity<?> removerCopiador(Long id) {
        Optional<Copiador> copiadorOptional = copiadorRepository.findById(id);
        if (copiadorOptional.isPresent()) {
            copiadorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
