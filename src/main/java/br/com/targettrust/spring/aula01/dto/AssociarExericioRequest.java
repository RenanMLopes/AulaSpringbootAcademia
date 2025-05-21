package br.com.targettrust.spring.aula01.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Value;

import java.util.List;
@Value
public class AssociarExericioRequest {

    @NotNull
    @Size(min = 1)
    private List<Integer> exercicios;



}
