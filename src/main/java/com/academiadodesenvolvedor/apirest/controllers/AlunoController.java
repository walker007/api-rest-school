package com.academiadodesenvolvedor.apirest.controllers;

import com.academiadodesenvolvedor.apirest.dtos.AlunoDTO;
import com.academiadodesenvolvedor.apirest.models.Aluno;
import com.academiadodesenvolvedor.apirest.repository.AlunoRepository;
import com.academiadodesenvolvedor.apirest.requests.CreateAlunoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoRepository alunoRepository;

    @Autowired
    public AlunoController(AlunoRepository repository) {
        this.alunoRepository = repository;
    }

    @PostMapping
    public ResponseEntity<AlunoDTO> cadastrar(@RequestBody @Validated CreateAlunoRequest request) {
        Aluno aluno = request.converter();
        this.alunoRepository.save(aluno);

        return new ResponseEntity<>(new AlunoDTO(aluno), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<AlunoDTO>> listar() {
        List<Aluno> alunoList = this.alunoRepository.findAll();
        List<AlunoDTO> alunos = alunoList.stream()
                .map(AlunoDTO::new)
                .toList();

        return new ResponseEntity<>(alunos, HttpStatus.OK);
    }
}
