package com.academiadodesenvolvedor.apirest.exceptions.handlers;

import com.academiadodesenvolvedor.apirest.dtos.ErroDTO;
import com.academiadodesenvolvedor.apirest.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ResourceNotFoundHandler {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(ResourceNotFoundException.class)
    public ErroDTO handle(ResourceNotFoundException error) {
        return new ErroDTO(error.getMessage());
    }
}
