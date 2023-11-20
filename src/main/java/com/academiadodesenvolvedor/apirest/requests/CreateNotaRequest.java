package com.academiadodesenvolvedor.apirest.requests;

import com.academiadodesenvolvedor.apirest.models.Nota;
import lombok.Data;

@Data
public class CreateNotaRequest {
    private String disciplina;
    private Double nota;

    public Nota convert() {
        Nota notaModel = new Nota();
        notaModel.setDisciplina(this.disciplina);
        notaModel.setNota(this.nota);
        return notaModel;
    }

    public Nota update(Nota notaModel) {
        notaModel.setDisciplina(this.disciplina);
        notaModel.setNota(this.nota);
        return notaModel;
    }
}
