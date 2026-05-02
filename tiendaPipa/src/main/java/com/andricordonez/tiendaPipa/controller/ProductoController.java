package com.andricordonez.tiendaPipa.controller;


import com.andricordonez.tiendaPipa.entity.DetalleVenta;
import com.andricordonez.tiendaPipa.entity.Producto;
import com.andricordonez.tiendaPipa.service.ProductoService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@Controller
@RequestMapping("/tienda/productos")
public class ProductoController {
    private ProductoService productoService;

    public ProductoController(ProductoService productoService) {
        this.productoService = productoService;
    }
    @GetMapping
    public String listarProducto(Model model) {
        model.addAttribute("producto", productoService.listarProducto());
        return "productos";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioCrear(Model model){
        model.addAttribute("producto", new Producto());
        model.addAttribute("modoEdicion", false);
        return "producto-form";
    }

    @PostMapping("/guardar")
    public String crearProducto(@Valid @ModelAttribute("producto") Producto producto, BindingResult result, Model model){
        if (result.hasErrors()){
            model.addAttribute("modoEdicion", false);
            return "producto-form";
        }
        productoService.crearProducto(producto);
        return "redirect:/tienda/productos";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarProducto(@PathVariable Integer id, Model model) {
        Producto producto = productoService.buscarPorIdProducto(id);

        model.addAttribute("producto", producto);
        model.addAttribute("modoEdicion", true);

        return "producto-form";
    }

    @PostMapping("/actualizar/{id}")
    public String actualizarProducto(@PathVariable Integer id, @Valid @ModelAttribute("producto") Producto producto, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("modoEdicion", true);
            return "producto-form";
        }
        productoService.actualizarProducto(id, producto);
        return "redirect:/tienda/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable Integer id){
        productoService.eliminarProducto(id);
        return "redirect:/tienda/productos";
    }
}
