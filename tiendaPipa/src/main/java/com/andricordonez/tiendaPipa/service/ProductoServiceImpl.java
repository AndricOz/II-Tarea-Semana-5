package com.andricordonez.tiendaPipa.service;


import com.andricordonez.tiendaPipa.entity.Producto;
import com.andricordonez.tiendaPipa.exception.ResourceNotFoundException;
import com.andricordonez.tiendaPipa.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listarProducto(){
        return productoRepository.findAll();
    }

    @Override
    public Producto crearProducto(Producto producto){
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizarProducto(Integer id, Producto producto){
        Producto existente = buscarPorIdProducto(id);
        existente.setNombreProducto(producto.getNombreProducto());
        existente.setPrecio(producto.getPrecio());
        existente.setStock(producto.getStock());
        existente.setEstado(producto.getEstado());
        return productoRepository.save(existente);
    }

    @Override
    public Producto buscarPorIdProducto(Integer id){
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ruta con codigo de producto: "+id+" no encontrada"));
    }

    @Override
    public void eliminarProducto(Integer id) {
        if (!productoRepository.existsById(id)) {
            throw  new ResourceNotFoundException("Ruta con codigo de producto: "+id+" no encontrada");
        }
        productoRepository.deleteById(id);
    }
}
