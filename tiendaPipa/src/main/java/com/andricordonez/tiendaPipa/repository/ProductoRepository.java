package com.andricordonez.tiendaPipa.repository;

import com.andricordonez.tiendaPipa.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
}
