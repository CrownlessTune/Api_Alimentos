package com.alimentos.inventory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register").permitAll()  // Permite el acceso sin autenticación a /login y /register
                        .anyRequest().authenticated()  // Requiere autenticación para cualquier otra ruta
                )
                .formLogin(login -> login
                        .loginPage("/login")  // Página de login personalizada
                        .loginProcessingUrl("/login")  // URL de procesamiento del login
                        .defaultSuccessUrl("/home", true)  // Redirige a /home al iniciar sesión exitosamente
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")  // URL para hacer logout
                        .logoutSuccessUrl("/login")  // Redirige a login después de logout
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            if ("usuario".equals(username)) {
                return User.withUsername("usuario")
                        .password(passwordEncoder().encode("password"))
                        .roles("USER")
                        .build();
            } else {
                throw new UsernameNotFoundException("Usuario no encontrado");
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
