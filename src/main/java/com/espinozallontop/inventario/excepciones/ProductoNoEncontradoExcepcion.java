package com.espinozallontop.inventario.excepciones;

public class ProductoNoEncontradoExcepcion extends RuntimeException {

	public ProductoNoEncontradoExcepcion(Integer id) {
		super("Producto "+id+" no encontrado!");
	}
	
}
