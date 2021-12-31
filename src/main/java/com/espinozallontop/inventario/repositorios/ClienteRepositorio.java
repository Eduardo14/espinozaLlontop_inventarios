package com.espinozallontop.inventario.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;

import com.espinozallontop.inventario.modelos.Cliente;

public interface ClienteRepositorio extends JpaRepository<Cliente, Integer>{

}
