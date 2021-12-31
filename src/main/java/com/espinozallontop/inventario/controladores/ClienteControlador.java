package com.espinozallontop.inventario.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.espinozallontop.inventario.excepciones.ClienteDatosIncorrectosExcepcion;
import com.espinozallontop.inventario.excepciones.ClienteNoEncontradoExcepcion;
import com.espinozallontop.inventario.logica.ValidaDatosCliente;
import com.espinozallontop.inventario.modelos.Cliente;
import com.espinozallontop.inventario.repositorios.ClienteRepositorio;

@RestController
public class ClienteControlador {

	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@PostMapping("clientes/crear")
	public void insertarCliente(
			@RequestParam(value="dni", required = true) String dni,
			@RequestParam(value="nombre", required = true) String nombre,
			@RequestParam(value="foto", required = false, defaultValue = "") String foto
	) {
		Cliente cliente = Cliente.builder().dni(dni).nombre(nombre).foto(foto).build();
		ValidaDatosCliente validaDatosCliente = new ValidaDatosCliente(cliente);
		if(!validaDatosCliente.dniVacio() && !validaDatosCliente.nombreVacio()) {
			clienteRepositorio.save(cliente);
		}else {
			throw new ClienteDatosIncorrectosExcepcion(cliente);
		}
	}

	@PostMapping("clientes/actualizar/{id}")
	public void actualizarCliente(
			@PathVariable Integer id,
			@RequestParam(value="dni", required = true) String dni,
			@RequestParam(value="nombre", required = true) String nombre,
			@RequestParam(value="foto", required = false, defaultValue = "") String foto
	) {
		Cliente cliente = clienteRepositorio.findById(id).orElseThrow(() -> new ClienteNoEncontradoExcepcion(id));
		cliente.setDni(dni);
		cliente.setNombre(nombre);
		cliente.setFoto(foto);
		ValidaDatosCliente validaDatosCliente = new ValidaDatosCliente(cliente);
		if(!validaDatosCliente.dniVacio() && !validaDatosCliente.nombreVacio()) {
			clienteRepositorio.save(cliente);
		}else {
			throw new ClienteDatosIncorrectosExcepcion(cliente);
		}
	}

	@GetMapping("clientes/eliminar/{id}")
	public void eliminarCliente(
			@PathVariable Integer id
	) {
		Cliente cliente = clienteRepositorio.findById(id).orElseThrow(() -> new ClienteNoEncontradoExcepcion(id));
		clienteRepositorio.deleteById(cliente.getId());
	}

	@GetMapping("clientes/leer/{id}")
	public Cliente leerCliente(
			@PathVariable Integer id
	) {
		return clienteRepositorio.findById(id).orElseThrow(() -> new ClienteNoEncontradoExcepcion(id));
	}

	@GetMapping("clientes/listar")
	public List<Cliente> leerCliente() {
		return clienteRepositorio.findAll();
	}
	
}
