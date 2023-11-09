package com.academiadodesenvolvedor.apirest.repository;

import com.academiadodesenvolvedor.apirest.models.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
