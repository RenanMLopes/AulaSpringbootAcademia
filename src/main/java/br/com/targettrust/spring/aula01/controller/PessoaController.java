package br.com.targettrust.spring.aula01.controller;

import br.com.targettrust.spring.aula01.dto.AssociarExericioRequest;
import br.com.targettrust.spring.aula01.model.Endereco;
import br.com.targettrust.spring.aula01.model.Exercicio;
import br.com.targettrust.spring.aula01.model.Pessoa;
import br.com.targettrust.spring.aula01.service.PessoaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.file.Path;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//identifica a classe como controller
@RestController
//diz qual é o caminho - exemplo: http://localhost:8080/pessoas
@RequestMapping(path = "/pessoas")
//notação para log.info
@Slf4j
//Cria um construtor para as propriedades final
@RequiredArgsConstructor
//padrao de projeto SINGLETON
public class PessoaController {


    //injeção de dependência por construtor, é a mais recomendada por conta de testes - a outra forma seria através do @AutoWired
    private final PessoaService pessoaService;


    //jackson para json transforma o json enviado em Pessoa por conta do @RequestBody
    @PostMapping()
    //Altera o status padrao 200 para 201, que significa objeto criado
    @ResponseStatus(HttpStatus.CREATED)
    //metodo para criar uma pessoa nova
    public Pessoa criarPessoa(@RequestBody Pessoa pessoa){
        return pessoaService.criarPessoa(pessoa);
    }

    @GetMapping
    public List<Pessoa> listarPessoas(
            @RequestParam(name = "nome", required = false) String nome,
            @RequestParam(name = "idade", required = false) Integer idade
    )
    {
        return pessoaService.listarPessoas(nome);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pessoa> findById(@PathVariable(name = "id") Integer id){
        return pessoaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    // metodo para editar a pessoa pelo id
    @PutMapping(path = "/{id}")
    public ResponseEntity<Pessoa> editarPessoa(@PathVariable(name = "id") Integer id, @RequestBody Pessoa pessoa){
       return pessoaService.editarPessoa(id, pessoa)
               .map(ResponseEntity::ok)
               .orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping
    public ResponseEntity<Pessoa> editarPessoaParcial(@PathVariable(name = "id") Integer id, @RequestBody Pessoa pessoa){
          return   pessoaService.editarParcial(id, pessoa)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
    }



    // metodo para deletar pessoa do banco pelo id
    @DeleteMapping(path = "/{id}")
    public void deletarPessoa(@PathVariable(name = "id") Integer id){
        pessoaService.deletarPessoa(id);
    }

    //metodo para associar exercicio a uma pessoa pelo id
    @PostMapping(path = "/{id}/exercicios")
    public void associarExercicios(
            @PathVariable ("id") Integer idPessoa,
            @RequestBody @Valid AssociarExericioRequest associarExericioRequest){
            log.info("Associar exercicio para pessoa de id: " + idPessoa);
            log.info(associarExericioRequest.getExercicios().stream()
                .map(Object::toString)
                .collect(Collectors.joining(",")));
    }
    //metodo para listar os exericios da pessoa
    @GetMapping(path = "/{id}/exercicios")
    public List<Exercicio> listarExercicios(
            @PathVariable ("id") Integer idPessoa){
        return new ArrayList<>();
    }

}
