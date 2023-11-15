package com.academiadodesenvolvedor.apirest.requests;

import com.academiadodesenvolvedor.apirest.models.Aluno;
import com.academiadodesenvolvedor.apirest.validations.EmailIsUnique;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateAlunoRequest {
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @NotEmpty
    @Size(min = 11, max = 11)
    private String cpf;
    @NotNull
    @NotEmpty
    @EmailIsUnique(entity = Aluno.class, message = "Este e-mail já está sendo usado")
    private String email;

    public Aluno converter() {
        Aluno aluno = new Aluno();
        aluno.setCpf(this.cpf);
        aluno.setNome(this.nome);
        aluno.setEmail(this.email);
        return aluno;
    }

    public Aluno update(Aluno aluno) {
        if (this.cpf != null) aluno.setCpf(this.cpf);
        if (this.nome != null) aluno.setNome(this.nome);
        if (this.email != null) aluno.setEmail(this.email);
        return aluno;
    }
}
