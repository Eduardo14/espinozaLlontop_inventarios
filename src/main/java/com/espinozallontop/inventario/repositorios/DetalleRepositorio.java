package com.espinozallontop.inventario.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espinozallontop.inventario.modelos.Detalle;

public interface DetalleRepositorio extends JpaRepository<Detalle, Integer> {

}
