package com.academiadodesenvolvedor.apirest.requests;

import com.academiadodesenvolvedor.apirest.models.Curso;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateCursoRequest {
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @NotEmpty
    private String descricao;

    public Curso converter() {
        Curso curso = new Curso();
        curso.setNome(this.nome);
        curso.setDescricao(this.descricao);

        return curso;
    }

    public Curso atualizar(Curso curso) {
        if (this.nome != null) curso.setNome(this.nome);

        if (this.descricao != null) curso.setDescricao(this.descricao);

        return curso;
    }
}
