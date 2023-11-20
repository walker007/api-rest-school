package com.academiadodesenvolvedor.apirest.dtos;

import com.academiadodesenvolvedor.apirest.models.Aluno;
import lombok.Data;

import java.util.List;

@Data
public class AlunoDTO {
    private String nome;
    private String email;
    private String cpf;
    private Long id;
    private DocumentosDTO documentos;
    private List<NotaDTO> notas;

    public AlunoDTO(Aluno aluno) {
        this.nome = aluno.getNome();
        this.email = aluno.getEmail();
        this.cpf = aluno.getCpf();
        this.id = aluno.getId();
        this.documentos = new DocumentosDTO(aluno.getDocumentos());
        this.notas = aluno.getNotas().stream().map(NotaDTO::new).toList();
    }
}
