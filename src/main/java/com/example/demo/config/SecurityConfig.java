package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // Como es una copia de pruebas, desactivamos CSRF y CORS aquí.
            .csrf(csrf -> csrf.disable())
            .cors(cors -> cors.disable())

            // Sin sesiones (REST stateless)
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            // ✅ Todo abierto: no requiere autenticación ni API Key
            .authorizeHttpRequests(auth -> auth
                .anyRequest().permitAll()
            );

        // ❌ Filtro de API Key desactivado (no lo registramos)
        // Si más adelante querés volver a activarlo:
        // http.addFilterBefore(apiKeyAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}


