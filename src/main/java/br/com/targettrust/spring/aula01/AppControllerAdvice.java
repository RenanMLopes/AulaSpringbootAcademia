package br.com.targettrust.spring.aula01;

import br.com.targettrust.spring.aula01.model.exceptions.RegistryNotFoundException;
import br.com.targettrust.spring.aula01.dto.ErrorResponse;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class AppControllerAdvice {

    @ExceptionHandler(RegistryNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleException(RegistryNotFoundException exception){
        return new ErrorResponse ("Registro não encontrado! Id:" + exception.getId());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handleException (RuntimeException exception){
        log.error(exception.getMessage(), exception);
        return new ErrorResponse("Ocorreu um Erro");
    }
}
