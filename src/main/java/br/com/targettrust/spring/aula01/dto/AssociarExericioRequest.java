package br.com.targettrust.spring.aula01.dto;

import java.util.List;

public class AssociarExericioRequest {
    public List<Integer> getExercicios() {
        return exercicios;
    }

    public void setExercicios(List<Integer> exercicios) {
        this.exercicios = exercicios;
    }

    private List<Integer> exercicios;
}
