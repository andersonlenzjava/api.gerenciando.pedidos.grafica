package grafica.pedidos.api.service.empregado;

import grafica.pedidos.api.domain.funcionario.empregado.contador.ContadorRegister;
import grafica.pedidos.api.domain.funcionario.empregado.contador.ContadorRepository;
import grafica.pedidos.api.domain.funcionario.empregado.contador.Contador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@Service
public class ContadorService {

    @Autowired
    private ContadorRepository contadorRepository;

    //Get
    public Page<ContadorRensponse> listarContador(String nomeContador, Pageable paginacao) {
        if(nomeContador == null) {
            Page<Contador> contadores = contadorRepository.findAll(paginacao);
            return ContadorRensponse.converter(contadores);
        } else {
            Page<Contador> contador = contadorRepository.findByFuncionarioNomeIgnoreCase(
                    nomeContador, paginacao);
            return ContadorRensponse.converterUmContador(contador);
        }
    }

    //Get id
    public ResponseEntity<ContadorRensponse> buscarContador(Long id) {
        Optional<Contador> contador = contadorRepository.findById(id);
        if (contador.isPresent()) {
            return ResponseEntity.ok(ContadorRensponse.converterUmContador(contador.get()));
        }
        return ResponseEntity.notFound().build();
    }

    //cadastrar
    public ResponseEntity<ContadorRensponse> cadastrarContador(ContadorRegister contadorRegister,
                                                                  UriComponentsBuilder uriBuilder) throws Exception {

        Optional<Contador> contadorOptional = contadorRepository.findByFuncionarioCpfIgnoreCase(
                contadorRegister.funcionarioRegister().cpf());

        if (contadorOptional.isEmpty()) {
            Contador contador = ContadorRegister.converter();
            contadorRepository.save(contador);

            URI uri = uriBuilder.path("/funcionario/gerenteFinaceiro/{id}").buildAndExpand(contador.getId()).toUri();
            return ResponseEntity.created(uri).body(new ContadorResponse(contador));
        } else {
            throw new ItemJaExisteException("Contador já existe");
        }
    }


    //atualizar
    public ResponseEntity<ContadorRensponse> atualizarContador(Long id, ContadorRegister contadorRegister) {
        Optional<Contador> contadorOptional = contadorRepository.findById(id);
        if (contadorOptional.isPresent()) {

            Contador contador = contadorOptional.get();
            contador.getFuncionario().setCpf(contadorRegister.funcionarioRegister().cpf());
            contador.getFuncionario().setNome(contadorRegister.funcionarioRegister().nome());
            contador.getFuncionario().setTelefone(contadorRegister.funcionarioRegister().telefone());
            contador.getFuncionario().setDataNascimento(contadorRegister.funcionarioRegister().dataNascimento());
            contador.getFuncionario().setStatusTrabalho(contadorRegister.funcionarioRegister().statusTrabalho());

            contadorRepository.save(contador);

            return ResponseEntity.ok(new ContadorResponse(contador));
        }
        return ResponseEntity.notFound().build();
    }

    //deletar
    public ResponseEntity<?> removerContador(Long id) {
        Optional<Contador> contadorOptional = contadorRepository.findById(id);
        if (contadorOptional.isPresent()) {
            contadorOptional.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


}
