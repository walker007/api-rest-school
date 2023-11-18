package com.academiadodesenvolvedor.apirest.exceptions.handlers;

import com.academiadodesenvolvedor.apirest.dtos.ErroDTO;
import com.academiadodesenvolvedor.apirest.exceptions.ValidateFailException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ValidateFailExceptionHandler {

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(ValidateFailException.class)
    public ErroDTO handler(ValidateFailException error) {
        return new ErroDTO(error.getMessage());
    }
}
