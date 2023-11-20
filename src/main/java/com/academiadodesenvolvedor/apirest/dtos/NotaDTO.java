package com.academiadodesenvolvedor.apirest.dtos;

import com.academiadodesenvolvedor.apirest.models.Nota;
import lombok.Data;

@Data
public class NotaDTO {
    private String disciplina;
    private Double nota;

    public NotaDTO(Nota notaModel) {
        this.disciplina = notaModel.getDisciplina();
        this.nota = notaModel.getNota();
    }
}
