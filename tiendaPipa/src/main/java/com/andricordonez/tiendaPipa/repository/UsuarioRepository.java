package com.andricordonez.tiendaPipa.repository;

import com.andricordonez.tiendaPipa.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Optional<Usuario> findByEmail(String email);
    Optional<Usuario> findByUserName(String userName);
    boolean existsByEmail(String email);
}
