package com.academiadodesenvolvedor.apirest.repository;

import com.academiadodesenvolvedor.apirest.models.Nota;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotaRepository extends JpaRepository<Nota, Long> {
}
