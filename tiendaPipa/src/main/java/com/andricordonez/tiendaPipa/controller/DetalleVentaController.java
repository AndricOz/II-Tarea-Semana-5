package com.andricordonez.tiendaPipa.controller;


import com.andricordonez.tiendaPipa.entity.Cliente;
import com.andricordonez.tiendaPipa.entity.DetalleVenta;
import com.andricordonez.tiendaPipa.service.DetalleVentaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@Controller
@RequestMapping("/tienda/detallesVentas")
public class DetalleVentaController {
    private DetalleVentaService  detalleVentaService;

    public DetalleVentaController(DetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public String listarDetalleVenta(Model model) {
        model.addAttribute("detalleVenta", detalleVentaService.listarDetalleVenta());
        return "detallesVentas";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model){
        model.addAttribute("detalleVenta", new DetalleVenta());
        model.addAttribute("modoEdicion", false);
        return "detalleVenta-form";
    }

    @PostMapping("/guardar")
    public String crearDetalleVenta(@Valid @ModelAttribute("detalleVenta") DetalleVenta detalleVenta, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("modoEdicion", false);
            return "detalleVenta-form";
        }
        detalleVentaService.crearDetalleVenta(detalleVenta);
        return "redirect:/tienda/detallesVentas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarDetalleVenta(@PathVariable Integer id, Model model) {
        DetalleVenta detalleVenta = detalleVentaService.buscarPorIdDetalleVenta(id);

        model.addAttribute("cliente", detalleVenta);
        model.addAttribute("modoEdicion", true);

        return "detalleVenta-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarDetalleVenta(@PathVariable Integer id, @Valid @ModelAttribute("detalleVenta") DetalleVenta detalleVenta, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "detalleVenta-form";
        }
        detalleVentaService.actualizarDetalleVenta(id, detalleVenta);
        return "redirect:/tienda/detallesVentas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVetalleVenta(@PathVariable Integer id){
        detalleVentaService.eliminarDetalleVenta(id);
        return "redirect:/tienda/detallesVentas";
    }
}
