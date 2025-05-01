package br.com.targettrust.spring.aula01.controller;

import br.com.targettrust.spring.aula01.model.Endereco;
import br.com.targettrust.spring.aula01.model.Pessoa;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

//identifica a classe como controller
@RestController
//diz qual é o caminho - exemplo: http://localhost:8080/pessoas
@RequestMapping(path = "/pessoas")
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
    public Pessoa criarPessoa(@RequestBody Pessoa pessoa){
        System.out.println(pessoa);
        pessoa.setId(32);
        return pessoa;
    }

    @GetMapping
    public List<Pessoa> listarPessoas() {
        return pessoas;
    }

}
