package com.andricordonez.tiendaPipa.service;

import com.andricordonez.tiendaPipa.entity.Venta;

import java.util.List;

public interface VentaService {
    List<Venta> listarVenta();
    Venta crearVenta(Venta venta);
    Venta actualizarVenta(Integer id, Venta venta);
    Venta buscarPorIdVenta(Integer id);
    void eliminarVenta(Integer id);
}
