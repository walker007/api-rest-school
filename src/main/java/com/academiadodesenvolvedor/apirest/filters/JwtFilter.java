package com.academiadodesenvolvedor.apirest.filters;

import com.academiadodesenvolvedor.apirest.services.JwtService;
import com.academiadodesenvolvedor.apirest.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;

    public JwtFilter(JwtService jwtService, UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException,
            IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {

            String token = authorizationHeader.split(" ")[1];
            boolean tokenIsValid = this.jwtService.tokenIsValid(token);
            if (tokenIsValid) {
                String email = this.jwtService.decode(token)
                        .getClaim("email")
                        .asString();
                UserDetails user = this.userService.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken authenticatedUser =
                        new UsernamePasswordAuthenticationToken(
                                user, null, null
                        );

                authenticatedUser
                        .setDetails(new WebAuthenticationDetailsSource()
                                .buildDetails(request));
                SecurityContextHolder.getContext()
                        .setAuthentication(authenticatedUser);
            }
        }

        filterChain.doFilter(request, response);

    }
}
