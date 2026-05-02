package com.andricordonez.tiendaPipa.service;

import com.andricordonez.tiendaPipa.entity.Cliente;

import java.util.List;

public interface ClienteService {
    List<Cliente> listarCliente();
    Cliente crearCliente(Cliente cliente);
    Cliente actualizarCliente(Integer id, Cliente cliente);
    Cliente buscarPorIdCliente(Integer id);
    void eliminarCliente(Integer id);
}
