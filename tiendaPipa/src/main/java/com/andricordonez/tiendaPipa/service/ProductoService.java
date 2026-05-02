package com.andricordonez.tiendaPipa.service;

import com.andricordonez.tiendaPipa.entity.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> listarProducto();
    Producto crearProducto(Producto producto);
    Producto actualizarProducto(Integer id, Producto producto);
    Producto buscarPorIdProducto(Integer id);
    void eliminarProducto(Integer id);
}
