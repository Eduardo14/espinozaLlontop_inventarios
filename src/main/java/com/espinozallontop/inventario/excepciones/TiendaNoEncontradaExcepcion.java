package com.espinozallontop.inventario.excepciones;

public class TiendaNoEncontradaExcepcion extends RuntimeException {

	public TiendaNoEncontradaExcepcion(Integer id) {
		super("Tienda "+id+" no encontrado!");
	}
	
}
