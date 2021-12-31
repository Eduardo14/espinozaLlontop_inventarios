package com.espinozallontop.inventario.configuracion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.espinozallontop.inventario.modelos.Producto;
import com.espinozallontop.inventario.repositorios.ProductoRepositorio;
import com.espinozallontop.inventario.servicios.ProductoServicio;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class DescargarProductos {

	@Autowired
	private ProductoServicio productoServicio;
	
	@Bean
	CommandLineRunner crearProductos(ProductoRepositorio productoRepositorio) {
		return args -> {
			Producto[] productos = productoServicio.getProductosMock();
			
			for (Producto producto : productos) {
				log.info("Descargando Producto "+producto.getId()+":" + productoRepositorio.save(producto));
			}
			
		};
	}
	
}
