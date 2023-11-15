package com.academiadodesenvolvedor.apirest.services;

import com.academiadodesenvolvedor.apirest.models.Usuario;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.UUID;

@Service
public class JwtService {
    @Value("${app.jwt.expiration}")
    private String expiracao;

    @Value("${app.jwt.secret}")
    private String assinatura;

    private Algorithm jwtAlgorithm() throws Exception {
        return Algorithm.HMAC256(this.assinatura);
    }

    public JWTVerifier verifier() throws Exception {
        return JWT.require(this.jwtAlgorithm()).build();
    }

    public DecodedJWT decode(String token) throws Exception {
        return this.verifier().verify(token);
    }

    public boolean tokenIsValid(String token) {
        try {
            DecodedJWT claims = this.decode(token);
            LocalDateTime dateExpiration = claims.getExpiresAt()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime();

            return !LocalDateTime.now().isAfter(dateExpiration);
        } catch (Exception e) {
            return false;
        }
    }

    public String generateToken(Usuario usuario) throws Exception {
        LocalDateTime expirationSeconds = LocalDateTime.now()
                .plusMinutes(Long.parseLong(this.expiracao));
        Date expiration = Date.from(expirationSeconds
                .atZone(ZoneId.systemDefault())
                .toInstant());

        return JWT.create()
                .withJWTId(UUID.randomUUID().toString())
                .withExpiresAt(expiration)
                .withIssuedAt(new Date())
                .withSubject(usuario.getId().toString())
                .withClaim("nome", usuario.getNome())
                .withClaim("email", usuario.getEmail())
                .sign(this.jwtAlgorithm())
                ;
    }
}
