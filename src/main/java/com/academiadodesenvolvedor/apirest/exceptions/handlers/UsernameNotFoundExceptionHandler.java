package com.academiadodesenvolvedor.apirest.exceptions.handlers;

import com.academiadodesenvolvedor.apirest.dtos.ErroDTO;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UsernameNotFoundExceptionHandler {

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UsernameNotFoundException.class)
    public ErroDTO handler(UsernameNotFoundException exception) {
        return new ErroDTO(exception.getMessage());
    }
}
