package com.andricordonez.tiendaPipa.controller;


import com.andricordonez.tiendaPipa.entity.Cliente;
import com.andricordonez.tiendaPipa.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@Controller
@RequestMapping("/tienda/clientes")
public class ClienteController {
    private ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listarCliente(Model model) {
        model.addAttribute("cliente", clienteService.listarCliente());
        return "clientes";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model){
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("modoEdicion", false);
        return "cliente-form";
    }

    @PostMapping("/guardar")
    public String crearCliente(@Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("modoEdicion", false);
            return "cliente-form";
        }
        clienteService.crearCliente(cliente);
        return "redirect:/tienda/clientes";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarCliente(@PathVariable Integer id, Model model) {
        Cliente cliente = clienteService.buscarPorIdCliente(id);

        model.addAttribute("cliente", cliente);
        model.addAttribute("modoEdicion", true);

        return "cliente-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarCliente(@PathVariable Integer id, @Valid @ModelAttribute("cliente") Cliente cliente, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "cliente-form";
        }
        clienteService.actualizarCliente(id, cliente);
        return "redirect:/tienda/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarCliente(@PathVariable Integer id){
        clienteService.eliminarCliente(id);
        return "redirect:/tienda/clientes";
    }
}
