package com.academiadodesenvolvedor.apirest.models;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;


@Data
@Entity(name = "cursos")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String descricao;
    @ManyToMany
    @JoinTable(name = "aluno_curso",
            joinColumns = @JoinColumn(name = "aluno_id"),
            inverseJoinColumns = @JoinColumn(name = "curso_id"))
    private List<Aluno> alunos;
}
