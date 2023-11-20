package com.academiadodesenvolvedor.apirest.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity(name = "alunos")
public class Aluno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    @ManyToMany(mappedBy = "alunos")
    private List<Curso> cursos;
    @OneToOne
    @JoinColumn(name = "documento_id")
    private Documentos documentos;

    @OneToMany(mappedBy = "aluno")
    private List<Nota> notas;
}
