package com.academiadodesenvolvedor.apirest.dtos;

import com.academiadodesenvolvedor.apirest.models.Aluno;
import lombok.Data;

@Data
public class AlunoDTO {
    private String nome;
    private String email;
    private String cpf;
    private Long id;

    public AlunoDTO(Aluno aluno) {
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
        this.cpf = aluno.getCpf();
        this.id = aluno.getId();
    }
}
