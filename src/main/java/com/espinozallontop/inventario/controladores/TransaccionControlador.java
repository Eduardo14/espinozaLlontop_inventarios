package com.espinozallontop.inventario.controladores;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.espinozallontop.inventario.excepciones.ClienteDatosIncorrectosExcepcion;
import com.espinozallontop.inventario.excepciones.ClienteNoEncontradoExcepcion;
import com.espinozallontop.inventario.excepciones.DetallesIncorrectosExcepcion;
import com.espinozallontop.inventario.excepciones.ProductoNoEncontradoExcepcion;
import com.espinozallontop.inventario.excepciones.ProductoStockMenorCeroExcepcion;
import com.espinozallontop.inventario.excepciones.TiendaNoEncontradaExcepcion;
import com.espinozallontop.inventario.excepciones.TransaccionExcepcion;
import com.espinozallontop.inventario.logica.ValidaDatosCliente;
import com.espinozallontop.inventario.logica.ValidaStock;
import com.espinozallontop.inventario.modelos.Cliente;
import com.espinozallontop.inventario.modelos.Detalle;
import com.espinozallontop.inventario.modelos.Producto;
import com.espinozallontop.inventario.modelos.Tienda;
import com.espinozallontop.inventario.modelos.Transaccion;
import com.espinozallontop.inventario.repositorios.ClienteRepositorio;
import com.espinozallontop.inventario.repositorios.DetalleRepositorio;
import com.espinozallontop.inventario.repositorios.ProductoRepositorio;
import com.espinozallontop.inventario.repositorios.TiendaRepositorio;
import com.espinozallontop.inventario.repositorios.TransaccionRepositorio;
import com.espinozallontop.inventario.servicios.ProductoServicio;

@RestController
public class TransaccionControlador {

	@Autowired
	private TransaccionRepositorio transaccionRepositorio;
	
	@Autowired
	private DetalleRepositorio detalleRepositorio;

	@Autowired
	private TiendaRepositorio tiendaRepositorio;

	@Autowired
	private ProductoRepositorio productoRepositorio;

	@Autowired
	private ClienteRepositorio clienteRepositorio;
	
	@Autowired
	private ProductoServicio productoServicio;
	
	@PostMapping("transacciones/registrar")
	public void insertarCliente(
			@RequestParam(value="cliente_id", required = true) Integer cliente_id,
			@RequestParam(value="tiendas_ids[]") Integer[] tiendas_ids,
			@RequestParam(value="productos_ids[]") Integer[] productos_ids,
			@RequestParam(value="cantidades[]") Integer[] cantidades
	) {
		Cliente cliente = clienteRepositorio.findById(cliente_id).orElseThrow(() -> new ClienteNoEncontradoExcepcion(cliente_id));
		if(tiendas_ids.length<=0 || productos_ids.length<=0 || cantidades.length<=0 || tiendas_ids.length!=productos_ids.length || tiendas_ids.length!=cantidades.length) {
			throw new DetallesIncorrectosExcepcion();
		}
		List<Detalle> detalles = new ArrayList<>();
		for (int i = 0; i < cantidades.length; i++) {
			Integer tienda_id = tiendas_ids[i];
			Integer producto_id = productos_ids[i];
			Integer cantidad = cantidades[i];
			Producto producto = productoRepositorio.findById(producto_id).orElseThrow(() -> new ProductoNoEncontradoExcepcion(producto_id));
			Tienda tienda = tiendaRepositorio.findById(tienda_id).orElseThrow(() -> new TiendaNoEncontradaExcepcion(tienda_id));
			Integer stock = producto.getStock();
			if(stock < cantidad) {
				if(cantidad - stock > 10) {
					throw new TransaccionExcepcion("Unidades no disponibles (> 10)");
				}else if(cantidad - stock > 5) {
					Producto productoStock = productoServicio.getStockExtra();
					Integer extra = productoStock.getStock();
					productoStock.setStock(productoStock.getStock() + stock - 5);
					ValidaStock validaStock = new ValidaStock(productoStock);
					if(validaStock.stockMenorIgualACero()) {
						throw new ProductoStockMenorCeroExcepcion(producto);
					}
					producto.setStock(producto.getStock() + extra);
				}
			}
			detalles.add(Detalle.builder().tienda_id(tienda.getId()).producto_id(producto_id).cantidad(cantidad).build());
		}
		Transaccion transaccion = Transaccion.builder().cliente_id(cliente_id).fecha(LocalDate.now()).hora(LocalTime.now()).build();
		transaccion = transaccionRepositorio.save(transaccion);
		for (Detalle detalle : detalles) {
			detalle.setTransaccion_id(transaccion.getId());
			Producto producto = productoRepositorio.findById(detalle.getProducto_id()).orElseThrow(() -> new ProductoNoEncontradoExcepcion(detalle.getProducto_id()));
			producto.setStock(producto.getStock() - detalle.getCantidad());
			productoRepositorio.save(producto);
			detalleRepositorio.save(detalle);
		}
		
	}
	
}
