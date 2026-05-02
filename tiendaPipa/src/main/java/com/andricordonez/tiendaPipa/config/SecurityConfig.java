package com.andricordonez.tiendaPipa.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final DaoAuthenticationProvider authenticationProvider;

    public SecurityConfig(DaoAuthenticationProvider authenticationProvider) {
        this.authenticationProvider = authenticationProvider;
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authenticationProvider(authenticationProvider)
                .authorizeHttpRequests(auth -> auth

                        // Las rutas publicas en las que todos ouden meterse
                        .requestMatchers("/", "/login", "/register", "/guardarRegistro", "/css/**").permitAll()

                        // Las rutas de admin que solo el puede entrar
                        .requestMatchers("/tienda/usuarios/**").hasRole("ADMIN")

                        // La autenticacion de admin y usuario
                        .requestMatchers("/tienda/**").hasAnyRole("ADMIN", "USER")

                        // Si la ruta no tiene persmisos tiene que requerir autenticacion
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/tienda/productos", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                )
                .exceptionHandling(ex -> ex
                        .accessDeniedPage("/acceso-denegado")  // Tengo que recordar que aqui esta el 403
                )
                .build();
    }
}
