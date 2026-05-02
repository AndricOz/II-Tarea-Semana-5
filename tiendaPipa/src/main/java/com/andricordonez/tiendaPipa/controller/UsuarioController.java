package com.andricordonez.tiendaPipa.controller;


import com.andricordonez.tiendaPipa.entity.Producto;
import com.andricordonez.tiendaPipa.entity.Usuario;
import com.andricordonez.tiendaPipa.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Controller
@RequestMapping("/tienda/usuarios")
public class UsuarioController {
    private UsuarioService usuarioService;
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listarUsuario(Model model) {
        model.addAttribute("usuario", usuarioService.listarUsuario());
        return "usuarios";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model){
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("modoEdicion", false);
        return "usuario-form";
    }

    @PostMapping("/guardar")
    public String crearUsuario(@Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("modoEdicion", false);
            return "usuario-form";
        }
        usuarioService.crearUsuario(usuario);
        return "redirect:/tienda/usuarios";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarUsuario(@PathVariable Integer id, Model model) {
        Usuario usuario = usuarioService.buscarPorIdUsuario(id);

        model.addAttribute("usuario", usuario);
        model.addAttribute("modoEdicion", true);

        return "usuario-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarUsuario(@PathVariable Integer id, @Valid @ModelAttribute("usuario") Usuario usuario, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "usuario-form";
        }
        usuarioService.actualizarUsuario(id, usuario);
        return "redirect:/tienda/usuarios";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id){
        usuarioService.eliminarUsuario(id);
        return "redirect:/tienda/usuarios";
    }
}
