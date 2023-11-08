package com.academiadodesenvolvedor.apirest.controllers;

import com.academiadodesenvolvedor.apirest.dtos.CursoDTO;
import com.academiadodesenvolvedor.apirest.exceptions.ResourceNotFoundException;
import com.academiadodesenvolvedor.apirest.models.Curso;
import com.academiadodesenvolvedor.apirest.repository.CursoRepository;
import com.academiadodesenvolvedor.apirest.requests.CreateCursoRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {
    private final CursoRepository repository;

    @Autowired
    public CursoController(CursoRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<CursoDTO>> listar() {
        List<Curso> cursos = this.repository.findAll();
        List<CursoDTO> cursoDTOS = cursos.stream()
                .map((curso) -> new CursoDTO(curso))
                .toList();

        return new ResponseEntity<>(cursoDTOS, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<CursoDTO> cadastraCurso(@RequestBody @Validated CreateCursoRequest request) {
        Curso curso = request.converter();
        this.repository.save(curso);

        return new ResponseEntity<>(new CursoDTO(curso), HttpStatus.CREATED);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity atualizaCurso(@PathVariable Long id, @RequestBody CreateCursoRequest request) {
        Optional<Curso> retorno = this.repository.findById(id);
        if (retorno.isEmpty()) {
            return new ResponseEntity<>("Nada encontrado", HttpStatus.NOT_FOUND);
        }
        Curso cursoAtual = request.atualizar(retorno.get());
        this.repository.save(cursoAtual);

        return new ResponseEntity(new CursoDTO(cursoAtual), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity obterCurso(@PathVariable Long id) {
        Optional<Curso> retorno = this.repository.findById(id);
        if (retorno.isEmpty()) {
            throw new ResourceNotFoundException("Curso não encontrado");
        }

        return new ResponseEntity(new CursoDTO(retorno.get()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity apagar(@PathVariable Long id) {
        Optional<Curso> retorno = this.repository.findById(id);
        if (retorno.isEmpty()) {
            throw new ResourceNotFoundException("Curso não encontrado");
        }

        this.repository.delete(retorno.get());
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
