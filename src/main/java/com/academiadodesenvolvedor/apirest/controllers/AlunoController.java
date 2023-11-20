package com.academiadodesenvolvedor.apirest.controllers;

import com.academiadodesenvolvedor.apirest.dtos.AlunoDTO;
import com.academiadodesenvolvedor.apirest.dtos.CursoDTO;
import com.academiadodesenvolvedor.apirest.exceptions.ResourceNotFoundException;
import com.academiadodesenvolvedor.apirest.exceptions.ValidateFailException;
import com.academiadodesenvolvedor.apirest.models.Aluno;
import com.academiadodesenvolvedor.apirest.models.Curso;
import com.academiadodesenvolvedor.apirest.models.Documentos;
import com.academiadodesenvolvedor.apirest.models.Nota;
import com.academiadodesenvolvedor.apirest.repository.AlunoRepository;
import com.academiadodesenvolvedor.apirest.repository.CursoRepository;
import com.academiadodesenvolvedor.apirest.repository.DocumentsRepository;
import com.academiadodesenvolvedor.apirest.repository.NotaRepository;
import com.academiadodesenvolvedor.apirest.requests.CreateAlunoRequest;
import com.academiadodesenvolvedor.apirest.requests.CreateNotaRequest;
import com.academiadodesenvolvedor.apirest.requests.DocumentsRequest;
import com.academiadodesenvolvedor.apirest.requests.EnrollRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoRepository alunoRepository;
    private final CursoRepository cursoRepository;
    private final DocumentsRepository documentsRepository;
    private final NotaRepository notaRepository;

    @Autowired
    public AlunoController(AlunoRepository repository,
                           CursoRepository cursoRepository,
                           DocumentsRepository documentsRepository,
                           NotaRepository notaRepository) {
        this.alunoRepository = repository;
        this.cursoRepository = cursoRepository;
        this.documentsRepository = documentsRepository;
        this.notaRepository = notaRepository;
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

    @GetMapping("/{id}")
    public ResponseEntity<AlunoDTO> getById(@PathVariable Long id) {
        Aluno aluno = this.alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
        return new ResponseEntity<>(new AlunoDTO(aluno), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlunoDTO> update(@PathVariable Long id,
                                           @RequestBody CreateAlunoRequest request) {
        Aluno aluno = this.alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));
        this.alunoRepository.save(request.update(aluno));

        return new ResponseEntity<>(new AlunoDTO(aluno), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        Aluno aluno = this.alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

        this.alunoRepository.delete(aluno);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    @PostMapping("/matricular")
    public ResponseEntity<CursoDTO> enroll(@RequestBody EnrollRequest request) {
        Aluno aluno = this.alunoRepository.findById(request.getAlunoId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

        Curso curso = this.cursoRepository.findById(request.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso não encontrado"));

        if (curso.getAlunos() == null) {
            List<Aluno> alunoList = Collections.singletonList(aluno);
            curso.setAlunos(alunoList);
            this.cursoRepository.save(curso);
            return new ResponseEntity(new CursoDTO(curso), HttpStatus.OK);
        }

        if (!curso.getAlunos().stream()
                .filter(aluno1 -> aluno1.getId() == aluno.getId())
                .toList().isEmpty()) {
            throw new ValidateFailException("Aluno já matriculado neste curso.");
        }

        List<Aluno> alunoList = curso.getAlunos();
        alunoList.add(aluno);
        curso.setAlunos(alunoList);
        this.cursoRepository.save(curso);
        return new ResponseEntity(new CursoDTO(curso), HttpStatus.OK);
    }

    @PutMapping("/{id}/documentos")
    private ResponseEntity<AlunoDTO> documents(@PathVariable Long id,
                                               @RequestBody DocumentsRequest request) {
        Aluno aluno = this.alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

        if (aluno.getDocumentos() == null) {
            Documentos documentos = request.convert();
            this.documentsRepository.save(documentos);
            aluno.setDocumentos(documentos);
            this.alunoRepository.save(aluno);
            return new ResponseEntity<>(new AlunoDTO(aluno), HttpStatus.OK);
        }

        aluno.setDocumentos(request.update(aluno.getDocumentos()));
        this.alunoRepository.save(aluno);
        return new ResponseEntity<>(new AlunoDTO(aluno), HttpStatus.OK);
    }

    @PostMapping("/{id}/nota")
    public ResponseEntity<AlunoDTO> nota(@PathVariable Long id,
                                         @RequestBody CreateNotaRequest request) {
        Aluno aluno = this.alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado"));

        Nota nota = request.convert();
        nota.setAluno(aluno);
        this.notaRepository.save(nota);

        return new ResponseEntity<>(new AlunoDTO(aluno), HttpStatus.CREATED);
    }
}
