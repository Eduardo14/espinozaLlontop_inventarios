package com.espinozallontop.inventario.excepciones;

import com.espinozallontop.inventario.modelos.Cliente;

public class ClienteDatosIncorrectosExcepcion extends RuntimeException {

	public ClienteDatosIncorrectosExcepcion(Cliente cliente) {
		super("El cliente tiene error en sus datos: DNI: "+cliente.getDni()+", Nombre: "+cliente.getNombre()+", Foto: "+cliente.getFoto()+"!");
	}
	
}
