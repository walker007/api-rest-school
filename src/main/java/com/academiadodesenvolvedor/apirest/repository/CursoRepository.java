package com.academiadodesenvolvedor.apirest.repository;

import com.academiadodesenvolvedor.apirest.models.Curso;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CursoRepository extends JpaRepository<Curso,Long> {
    //@Query([instrução]) Somente se o nome não for semântico
    //[tipo de retorno] [nome do método] ([...parâmetros]);
}
