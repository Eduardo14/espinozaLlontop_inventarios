package com.espinozallontop.inventario.excepciones;

public class ClienteNoEncontradoExcepcion extends RuntimeException {

	public ClienteNoEncontradoExcepcion(Integer id) {
		super("Cliente "+id+" no encontrado!");
	}
	
}
