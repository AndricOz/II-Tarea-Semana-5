package com.andricordonez.tiendaPipa.controller;

import com.andricordonez.tiendaPipa.entity.Usuario;
import com.andricordonez.tiendaPipa.service.UsuarioService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AuthController {

    private final UsuarioService usuarioService;

    public AuthController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "register";
    }

    @PostMapping("/guardarRegistro")
    public String guardarRegistro(@ModelAttribute Usuario usuario, Model model) {
        try {
            usuario.setRol("USER");
            usuario.setEstado(true);
            usuarioService.crearUsuario(usuario);
            return "redirect:/login?registroExitoso";

        } catch (IllegalArgumentException e) {
            // Es para emails que ya existan
            model.addAttribute("usuario", usuario);
            model.addAttribute("errorEmail", e.getMessage());
            return "register";
        }
    }

    @GetMapping("/acceso-denegado")
    public String accesoDenegado() {
        return "acceso-denegado";
    }
}
