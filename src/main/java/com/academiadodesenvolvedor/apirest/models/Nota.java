package com.academiadodesenvolvedor.apirest.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Nota {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String disciplina;
    private Double nota;
    
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;
}
