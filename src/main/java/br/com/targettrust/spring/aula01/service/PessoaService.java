package br.com.targettrust.spring.aula01.service;

import br.com.targettrust.spring.aula01.model.Endereco;
import br.com.targettrust.spring.aula01.model.Pessoa;
import br.com.targettrust.spring.aula01.model.exceptions.RegistryNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.rmi.registry.Registry;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PessoaService {
    private List<Endereco> enderecos = List.of(
            new Endereco("Leandro bispo de oliveira", 178),
            new Endereco("Maria aparecida zago", 117)

    );

    private List<Pessoa> pessoas = new ArrayList<>(
            List.of(
                    new Pessoa(1, "Renan Lopes", "12345678901", LocalDate.now().minus(28, ChronoUnit.YEARS),enderecos),
                    new Pessoa(2, "Leticia Egle", "12345678901", LocalDate.now().minus(26, ChronoUnit.YEARS),enderecos),
                    new Pessoa(3, "Adriana Lopes", "12345678901", LocalDate.now().minus(50, ChronoUnit.YEARS),enderecos)

            )
    );
    public List<Pessoa> listarPessoas(String nome){
        return pessoas.stream()
                .filter(pessoa -> pessoa.getNome().contains(nome))
                .collect(Collectors.toList());
    }

    //metodo para localizar a pessoa pelo id
    private int localizarPessoa(Integer id) {
        for(int index = 0; index < pessoas.size(); index++){
            Pessoa searchPessoa = pessoas.get(index);
            if(id.equals(searchPessoa.getId())) {
                return index;
            }
        }
        return -1;
    }

    public Pessoa criarPessoa(Pessoa pessoa) {
        pessoa.setId(pessoas.size() + 1);
        pessoas.add(pessoa);
        return pessoa;
    }

    public Optional <Pessoa> findById(Integer id) {
        int index = localizarPessoa(id);
        if (index!= -1){
            return Optional.of(pessoas.get(index));
        }else{
            return Optional.empty();
        }

    }

    public Optional<Pessoa> editarPessoa(Integer id, Pessoa pessoa) {
        int posicao= localizarPessoa(id);
        if (posicao != -1) {
            pessoa.setId(id);
            pessoas.set(posicao, pessoa);
            return Optional.of(pessoa);
        }else{
            return Optional.empty();
        }


    }

    public Optional<Pessoa> editarParcial(Integer id, Pessoa pessoa) {
        int posicao= localizarPessoa(id);
        if (posicao != -1) {
            pessoa.setId(id);
            pessoas.set(posicao, pessoa);
            return Optional.of(pessoa);
        }else{
            return Optional.empty();
        }
    }

    public void deletarPessoa(Integer id) {
        int posicao = localizarPessoa(id);
        if (posicao != -1) {
            pessoas.remove(posicao);
        }else{
             throw new RegistryNotFoundException(id);
        }
    }
}
