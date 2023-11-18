package com.academiadodesenvolvedor.apirest.exceptions;

public class ValidateFailException extends RuntimeException {
    public ValidateFailException(String message) {
        super(message);
    }
}
