package com.academiadodesenvolvedor.apirest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class InputErrorDTO {
    private String field;
    private String error;
}
