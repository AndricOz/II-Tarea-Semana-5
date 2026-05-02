package com.andricordonez.tiendaPipa.controller;

import com.andricordonez.tiendaPipa.entity.Venta;
import com.andricordonez.tiendaPipa.service.VentaService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@Validated
@Controller
@RequestMapping("tienda/ventas")
public class VentaController {
    private VentaService ventaService;
    public VentaController(VentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public String listarVenta(Model model) {
        model.addAttribute("venta", ventaService.listarVenta());
        return "ventas";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model){
        model.addAttribute("venta", new Venta());
        model.addAttribute("modoEdicion", false);
        return "venta-form";
    }

    @PostMapping("/guardar")
    public String crearVenta(@Valid @ModelAttribute("venta") Venta venta, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("modoEdicion", false);
            return "venta-form";
        }
        ventaService.crearVenta(venta);
        return "redirect:/tienda/ventas";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarVenta(@PathVariable Integer id, Model model) {
        Venta venta = ventaService.buscarPorIdVenta(id);

        model.addAttribute("venta", venta);
        model.addAttribute("modoEdicion", true);

        return "venta-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarVenta(@PathVariable Integer id, @Valid @ModelAttribute("venta") Venta venta, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "venta-form";
        }
        ventaService.actualizarVenta(id, venta);
        return "redirect:/tienda/ventas";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarVenta(@PathVariable Integer id){
        ventaService.eliminarVenta(id);
        return "redirect:/tienda/ventas";
    }
}
