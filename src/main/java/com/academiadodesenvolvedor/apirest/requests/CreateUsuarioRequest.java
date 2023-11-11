package com.academiadodesenvolvedor.apirest.requests;

import com.academiadodesenvolvedor.apirest.models.Usuario;
import com.academiadodesenvolvedor.apirest.validations.EmailIsUnique;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateUsuarioRequest {
    @NotNull
    @NotEmpty
    private String nome;
    @NotNull
    @NotEmpty
    @EmailIsUnique(entity = Usuario.class, message = "Este email já está em uso")
    private String email;
    @NotNull
    @NotEmpty
    private String password;

    public Usuario converter() {
        Usuario usuario = new Usuario();
        usuario.setEmail(this.email);
        usuario.setNome(this.nome);
        usuario.setPassword(this.password);
        return usuario;
    }

    public Usuario atualizar(Usuario usuario) {
        if (this.email != null) usuario.setEmail(this.email);
        if (this.nome != null) usuario.setNome(this.nome);
        return usuario;
    }
}
