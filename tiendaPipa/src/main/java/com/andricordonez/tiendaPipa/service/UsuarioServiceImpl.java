package com.andricordonez.tiendaPipa.service;


import com.andricordonez.tiendaPipa.entity.Usuario;
import com.andricordonez.tiendaPipa.exception.ResourceNotFoundException;
import com.andricordonez.tiendaPipa.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public List<Usuario> listarUsuario(){
        return usuarioRepository.findAll();
    }
    @Override
    public Usuario crearUsuario(Usuario usuario) {

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        String passwordHash = passwordEncoder.encode(usuario.getPassword());
        usuario.setPassword(passwordHash);

        return usuarioRepository.save(usuario);
    }
    @Override
    public Usuario actualizarUsuario(Integer id, Usuario usuario){
        Usuario existente = buscarPorIdUsuario(id);
        existente.setUserName(usuario.getUserName());
        existente.setEmail(usuario.getEmail());
        existente.setRol(usuario.getRol());
        existente.setEstado(usuario.getEstado());
        if (usuario.getPassword() != null && !usuario.getPassword().isBlank()) {
            String newPasswordHash = passwordEncoder.encode(usuario.getPassword());
            existente.setPassword(newPasswordHash);
        }
        return usuarioRepository.save(existente);
    }
    @Override
    public Usuario buscarPorIdUsuario(Integer id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ruta con codigo de usuario: "+id+" no encontrada"));
    }
    @Override
    public void eliminarUsuario(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw  new ResourceNotFoundException("Ruta con codigo de usuario: "+id+" no encontrada");
        }
        usuarioRepository.deleteById(id);
    }
}
