package com.academiadodesenvolvedor.apirest.models;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Documentos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private boolean cpf;
    private boolean identidade;
    private boolean comprovanteEndereco;
    private boolean certidaoNascimento;
    @OneToOne(mappedBy = "documentos")
    private Aluno aluno;

}
