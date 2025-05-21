package br.com.targettrust.spring.aula01.model;



import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
//classe para cadastro de pessoa
public class Pessoa {

    private Integer id;
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    //lista de endereços
    private List<Endereco> enderecos;


}

