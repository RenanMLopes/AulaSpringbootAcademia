package br.com.targettrust.spring.aula01.controller;

import br.com.targettrust.spring.aula01.dto.AssociarExericioRequest;
import br.com.targettrust.spring.aula01.model.Endereco;
import br.com.targettrust.spring.aula01.model.Exercicio;
import br.com.targettrust.spring.aula01.model.Pessoa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

//identifica a classe como controller
@RestController
//diz qual é o caminho - exemplo: http://localhost:8080/pessoas
@RequestMapping(path = "/pessoas")
//padrao de projeto SINGLETON
public class PessoaController {
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
    //jackson para json transforma o json enviado em Pessoa por conta do @RequestBody
    @PostMapping()
    //Altera o status padrao 200 para 201, que significa objeto criado
    @ResponseStatus(HttpStatus.CREATED)
    //metodo para criar uma pessoa nova
    public Pessoa criarPessoa(@RequestBody Pessoa pessoa){
        pessoa.setId(pessoas.size() + 1);
        pessoas.add(pessoa);
        return pessoa;
    }

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoas;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable(name = "id") Integer id){
        int index = localzarPessoa(id);
        if (index!= -1){
            return ResponseEntity.ok(pessoas.get(index));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    // metodo para editar a pessoa pelo id
    @PutMapping(path = "/{id}")
    public ResponseEntity<Pessoa> editarPessoa(@PathVariable(name = "id") Integer id, @RequestBody Pessoa pessoa){
        int posicao= localzarPessoa(id);
        if (posicao != -1) {
            pessoa.setId(id);
            pessoas.set(posicao, pessoa);
            return ResponseEntity.ok(pessoa);
        }else{
           return ResponseEntity.notFound().build();
        }

    }

    //metodo para localizar a pessoa pelo d
    private int localzarPessoa(Integer id) {
        for(int index = 0; index < pessoas.size(); index++){
            Pessoa searchPessoa = pessoas.get(index);
            if(id.equals(searchPessoa.getId())) {
               return index;
            }
        }
        return -1;
    }

    // metodo para deletar pessoa do banco pelo id
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deletarPessoa(@PathVariable(name = "id") Integer id){
        int posicao = localzarPessoa(id);
        if (posicao != -1) {
            pessoas.remove(posicao);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    //metodo para associar exercicio a uma pessoa pelo id
    @PostMapping(path = "/{id}/exercicios")
    public void associarExercicios(
            @PathVariable ("id") Integer idPessoa,
            @RequestBody AssociarExericioRequest associarExericioRequest){
    }

    //metodo para listar os exericios da pessoa
    @GetMapping(path = "/{id}/exercicios")
    public List<Exercicio> listarExercicios(
            @PathVariable ("id") Integer idPessoa){
        return new ArrayList<>();
    }

}
