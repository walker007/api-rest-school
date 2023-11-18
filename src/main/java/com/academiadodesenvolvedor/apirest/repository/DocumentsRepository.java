package com.academiadodesenvolvedor.apirest.repository;

import com.academiadodesenvolvedor.apirest.models.Documentos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentsRepository extends JpaRepository<Documentos, Long> {
}
