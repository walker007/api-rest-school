package com.academiadodesenvolvedor.apirest.dtos;

import com.academiadodesenvolvedor.apirest.models.Curso;
import lombok.Data;

@Data
public class CursoDTO {
    public Long id;
    public String nome;
    public String descricao;

    public CursoDTO(Curso curso){
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.descricao = curso.getDescricao();
    }
}
