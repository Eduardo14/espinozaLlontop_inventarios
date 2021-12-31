package com.espinozallontop.inventario.logica;

import com.espinozallontop.inventario.modelos.Cliente;

public class ValidaDatosCliente {

	private Cliente cliente;
	
	public ValidaDatosCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public boolean dniVacio() {
		return this.cliente.getDni() == null || this.cliente.getDni().trim().isEmpty();
	}

	public boolean nombreVacio() {
		return this.cliente.getNombre() == null || this.cliente.getNombre().trim().isEmpty();
	}
	
}
