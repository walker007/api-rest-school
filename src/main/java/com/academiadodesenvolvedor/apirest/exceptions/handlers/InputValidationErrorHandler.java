package com.academiadodesenvolvedor.apirest.exceptions.handlers;

import com.academiadodesenvolvedor.apirest.dtos.InputErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class InputValidationErrorHandler {

    MessageSource messageSource;

    @Autowired
    public InputValidationErrorHandler(MessageSource ms) {
        this.messageSource = ms;
    }

    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public List<InputErrorDTO> handle(MethodArgumentNotValidException erro) {
        List<FieldError> errors = erro.getBindingResult().getFieldErrors();

        return errors.stream()
                .map(campoErro -> {
                    String mensagem = this.messageSource.getMessage(campoErro, LocaleContextHolder.getLocale());
                    return new InputErrorDTO(campoErro.getField(), mensagem);
                })
                .toList();
    }

}
