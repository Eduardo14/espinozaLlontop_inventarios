package com.espinozallontop.inventario.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espinozallontop.inventario.modelos.Transaccion;

public interface TransaccionRepositorio extends JpaRepository<Transaccion, Integer> {

}
