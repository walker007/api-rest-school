package com.academiadodesenvolvedor.apirest.dtos;

import com.academiadodesenvolvedor.apirest.models.Curso;
import lombok.Data;

import java.util.List;

@Data
public class CursoDTO {
    public Long id;
    public String nome;
    public String descricao;
    public List<AlunoDTO> alunos;

    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.nome = curso.getNome();
        this.descricao = curso.getDescricao();
        this.alunos = curso.getAlunos().stream()
                .map(AlunoDTO::new)
                .toList();
    }
}
