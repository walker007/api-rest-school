package com.academiadodesenvolvedor.apirest.controllers;

import com.academiadodesenvolvedor.apirest.dtos.LoginDTO;
import com.academiadodesenvolvedor.apirest.dtos.UsuarioDTO;
import com.academiadodesenvolvedor.apirest.models.Usuario;
import com.academiadodesenvolvedor.apirest.repository.UserRepository;
import com.academiadodesenvolvedor.apirest.requests.CreateUsuarioRequest;
import com.academiadodesenvolvedor.apirest.requests.LoginRequest;
import com.academiadodesenvolvedor.apirest.services.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
    private final JwtService jwtService;

    @Autowired
    public AutenticacaoController(UserRepository userRepository,
                                  PasswordEncoder passwordEncoder,
                                  JwtService jwtService) {
        this.userRepository = userRepository;
        this.encoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @PostMapping("/create")
    public ResponseEntity<UsuarioDTO> createUser(
            @RequestBody @Validated CreateUsuarioRequest request) {
        Usuario usuario = request.converter();

        usuario.setPassword(this.encoder.encode(request.getPassword()));

        this.userRepository.save(usuario);

        return new ResponseEntity<>(new UsuarioDTO(usuario), HttpStatus.CREATED);
    }

    @PostMapping
    public ResponseEntity<LoginDTO> login(@RequestBody @Validated LoginRequest request) throws Exception {
        Usuario usuario = this.userRepository
                .findByEmail(request.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("E-mail e/ou senha inválidos"));

        boolean isPasswordValid = this.encoder
                .matches(request.getPassword(), usuario.getPassword());

        if (!isPasswordValid) {
            throw new UsernameNotFoundException("E-mail e/ou senha inválidos");
        }

        String token = this.jwtService.generateToken(usuario);

        return new ResponseEntity<>(new LoginDTO("Bearer", token), HttpStatus.CREATED);

    }


}
