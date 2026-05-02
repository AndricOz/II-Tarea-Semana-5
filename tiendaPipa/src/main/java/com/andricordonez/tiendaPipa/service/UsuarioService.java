package com.andricordonez.tiendaPipa.service;

import com.andricordonez.tiendaPipa.entity.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> listarUsuario();
    Usuario crearUsuario(Usuario usuario);
    Usuario actualizarUsuario(Integer id, Usuario usuario);
    Usuario buscarPorIdUsuario(Integer id);
    void eliminarUsuario(Integer id);
}