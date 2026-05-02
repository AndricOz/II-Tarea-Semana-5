package com.andricordonez.tiendaPipa.config;

import com.andricordonez.tiendaPipa.entity.Usuario;
import com.andricordonez.tiendaPipa.repository.UsuarioRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String identificador) throws UsernameNotFoundException {
        // Aqui es donde busco por username o email
        Usuario usuario = usuarioRepository.findByUserName(identificador)
                .or(() -> usuarioRepository.findByEmail(identificador))
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Usuario no encontrado con: " + identificador));

        /*Recordatorio:
       Descubri que spring security necesita el "ROLE_"
       */
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("ROLE_" + usuario.getRol());

        return new User(
                usuario.getUserName(),
                usuario.getPassword(),
                List.of(authority)
        );
    }
}
