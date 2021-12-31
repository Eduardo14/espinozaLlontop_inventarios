package com.espinozallontop.inventario.excepciones;

import com.espinozallontop.inventario.modelos.Producto;

public class ProductoStockMenorCeroExcepcion extends RuntimeException {

	public ProductoStockMenorCeroExcepcion(Producto producto) {
		super("Error en el stock ("+producto.getStock()+") del producto "+producto.getName()+"!");
	}
	
}
