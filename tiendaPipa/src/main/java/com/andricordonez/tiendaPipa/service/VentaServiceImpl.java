package com.andricordonez.tiendaPipa.service;

import com.andricordonez.tiendaPipa.entity.Venta;
import com.andricordonez.tiendaPipa.exception.ResourceNotFoundException;
import com.andricordonez.tiendaPipa.repository.VentaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {
    private final VentaRepository ventaRepository;

    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<Venta> listarVenta() {
        return ventaRepository.findAll();
    }

    @Override
    public Venta crearVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    @Override
    public Venta actualizarVenta(Integer id, Venta venta) {
        Venta existente = buscarPorIdVenta(id);
        existente.setFechaVenta(venta.getFechaVenta());
        existente.setTotal(venta.getTotal());
        existente.setEstado(venta.getEstado());
        existente.setClientesDpiCliente(venta.getClientesDpiCliente());
        existente.setUsuariosCodigoUsuario(venta.getUsuariosCodigoUsuario());
        return ventaRepository.save(venta);
    }

    @Override
    public Venta buscarPorIdVenta(Integer id) {
        return ventaRepository.findById(id).orElseThrow(() -> new RuntimeException("Ruta con codigo de venta: "+id+" no encontrada"));
    }

    @Override
    public void eliminarVenta(Integer id) {
        if (ventaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Ruta con codigo de venta: "+id+" no encontrada");
        }
        ventaRepository.deleteById(id);
    }
}
