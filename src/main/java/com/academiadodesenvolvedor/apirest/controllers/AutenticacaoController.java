package com.academiadodesenvolvedor.apirest.controllers;

import com.academiadodesenvolvedor.apirest.dtos.UsuarioDTO;
import com.academiadodesenvolvedor.apirest.models.Usuario;
import com.academiadodesenvolvedor.apirest.repository.UserRepository;
import com.academiadodesenvolvedor.apirest.requests.CreateUsuarioRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public AutenticacaoController(UserRepository userRepository,
                                  PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
    }

    @PostMapping("/create")
    public ResponseEntity<UsuarioDTO> createUser(
            @RequestBody @Validated CreateUsuarioRequest request) {
        Usuario usuario = request.converter();

        usuario.setPassword(this.encoder.encode(request.getPassword()));

        this.userRepository.save(usuario);

        return new ResponseEntity<>(new UsuarioDTO(usuario), HttpStatus.CREATED);
    }


}
