package br.com.targettrust.spring.aula01.controller;

import br.com.targettrust.spring.aula01.model.Exercicio;
import br.com.targettrust.spring.aula01.model.Pessoa;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/exercicios")
public class ExercicioController {

    //lista de exercicios para teste, mais para frente farei a integração com o banco
    private List<Exercicio> exericios = new ArrayList<>(
            List.of(
                    new Exercicio(1, "Alteres", "peito"),
                    new Exercicio(2, "Supino", "Peito"),
                    new Exercicio(3, "Agachamento", "Perna")

            )
    );
    @PostMapping()
    //Altera o status padrao 200 para 201, que significa objeto criado
    @ResponseStatus(HttpStatus.CREATED)
    //metodo para criar um exercicio novo
    public Exercicio criarExericio (@RequestBody Exercicio exercicio){
        exercicio.setId(exericios.size() + 1);
        exericios.add(exercicio);
        return exercicio;
    }

    @GetMapping
    public List<Exercicio> listarExercicios() {
        return exericios;
    }
    @GetMapping("/{id}")
    public ResponseEntity<Exercicio> findById(@PathVariable(name = "id") Integer id){
        int index = localizarExercicio(id);
        if (index!= -1){
            return ResponseEntity.ok(exericios.get(index));
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    // metodo para editar um exercicio pelo id
    @PutMapping(path = "/{id}")
    public ResponseEntity<Exercicio> editarExericio(@PathVariable(name = "id") Integer id, @RequestBody Exercicio exercicio){
        int posicao= localizarExercicio(id);
        if (posicao != -1) {
            exercicio.setId(id);
            exericios.set(posicao, exercicio);
            return ResponseEntity.ok(exercicio);
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    //metodo para localizar a Exercicio pelo d
    private int localizarExercicio(Integer id) {
        for(int index = 0; index < exericios.size(); index++){
            Exercicio searchExercicio = exericios.get(index);
            if(id.equals(searchExercicio.getId())) {
                return index;
            }
        }
        return -1;
    }

    // metodo para deletar um exercicio do banco pelo id
    @DeleteMapping(path = "/{id}")
    public ResponseEntity deletarExercicio(@PathVariable(name = "id") Integer id){
        int posicao = localizarExercicio(id);
        if (posicao != -1) {
            exericios.remove(posicao);
            return ResponseEntity.ok().build();
        }else{
            return ResponseEntity.notFound().build();
        }
    }
}
