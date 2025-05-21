package br.com.targettrust.spring.aula01.model.exceptions;


import lombok.Value;

@Value
public class RegistryNotFoundException extends RuntimeException {
    private final int id;

}
