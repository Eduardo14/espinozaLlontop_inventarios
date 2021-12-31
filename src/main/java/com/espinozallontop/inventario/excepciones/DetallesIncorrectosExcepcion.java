package com.espinozallontop.inventario.excepciones;

public class DetallesIncorrectosExcepcion extends RuntimeException {

	public DetallesIncorrectosExcepcion() {
		super("Los detalles de la transaccion son incorrectos!");
	}
	
}
