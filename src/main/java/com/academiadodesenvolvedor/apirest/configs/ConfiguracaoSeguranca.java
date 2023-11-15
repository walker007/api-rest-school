package com.academiadodesenvolvedor.apirest.configs;

import com.academiadodesenvolvedor.apirest.filters.JwtFilter;
import com.academiadodesenvolvedor.apirest.services.JwtService;
import com.academiadodesenvolvedor.apirest.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class ConfiguracaoSeguranca {

    private final JwtService jwtService;
    private final UserService userService;

    @Autowired
    public ConfiguracaoSeguranca(JwtService jwtService,
                                 UserService userService) {
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "/login/**")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/cursos")
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(new JwtFilter(this.jwtService, this.userService),
                        UsernamePasswordAuthenticationFilter.class)
        ;

        return http.build();
    }
}
