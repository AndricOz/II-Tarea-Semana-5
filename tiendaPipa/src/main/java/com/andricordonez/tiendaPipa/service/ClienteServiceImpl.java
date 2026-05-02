package com.andricordonez.tiendaPipa.service;


import com.andricordonez.tiendaPipa.entity.Cliente;
import com.andricordonez.tiendaPipa.exception.ResourceNotFoundException;
import com.andricordonez.tiendaPipa.repository.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository clienteRepository;

    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }
    @Override
    public List<Cliente> listarCliente(){
        return clienteRepository.findAll();
    }
    @Override
    public Cliente crearCliente(Cliente cliente){
        return clienteRepository.save(cliente);
    }
    @Override
    public Cliente actualizarCliente(Integer id, Cliente cliente){
        Cliente existente = buscarPorIdCliente(id);
        existente.setNombreCliente(cliente.getNombreCliente());
        existente.setApellidoCliente(cliente.getApellidoCliente());
        existente.setDpiCliente(cliente.getDpiCliente());
        existente.setDireccion(cliente.getDireccion());
        existente.setEstado(cliente.getEstado());
        return clienteRepository.save(existente);
    }
    @Override
    public Cliente buscarPorIdCliente(Integer id){
        return clienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ruta con dpi: "+id+" no encontrada"));
    }
    @Override
    public void eliminarCliente(Integer id) {
        if (!clienteRepository.existsById(id)) {
            throw  new ResourceNotFoundException("Ruta con dpi: "+id+" no encontrada");
        }
        clienteRepository.deleteById(id);
    }
}
