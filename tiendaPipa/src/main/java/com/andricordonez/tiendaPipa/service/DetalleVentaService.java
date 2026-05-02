package com.andricordonez.tiendaPipa.service;

import com.andricordonez.tiendaPipa.entity.DetalleVenta;

import java.util.List;

public interface DetalleVentaService {
    List<DetalleVenta> listarDetalleVenta();
    DetalleVenta crearDetalleVenta(DetalleVenta detalleVenta);
    DetalleVenta actualizarDetalleVenta(Integer id,DetalleVenta detalleVenta);
    DetalleVenta buscarPorIdDetalleVenta(Integer id);
    void eliminarDetalleVenta(Integer id);
}
