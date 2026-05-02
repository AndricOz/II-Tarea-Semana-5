package com.andricordonez.tiendaPipa.repository;

import com.andricordonez.tiendaPipa.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
