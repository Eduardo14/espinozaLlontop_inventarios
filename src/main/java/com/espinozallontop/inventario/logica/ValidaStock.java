package com.espinozallontop.inventario.logica;

import com.espinozallontop.inventario.modelos.Producto;

public class ValidaStock {

	private Producto producto;
	
	public ValidaStock(Producto producto) {
		this.producto = producto;
	}
	
	public boolean stockMenorIgualACero() {
		return this.producto.getStock() <= 0;
	}
	
}
