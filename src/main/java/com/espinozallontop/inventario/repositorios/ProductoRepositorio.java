package com.espinozallontop.inventario.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espinozallontop.inventario.modelos.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer>{

}
