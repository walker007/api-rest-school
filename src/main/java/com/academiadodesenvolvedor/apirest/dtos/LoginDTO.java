package com.academiadodesenvolvedor.apirest.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginDTO {
    private String type;
    private String token;
}
