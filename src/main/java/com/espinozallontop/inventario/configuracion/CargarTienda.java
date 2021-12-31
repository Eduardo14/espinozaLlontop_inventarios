package com.espinozallontop.inventario.configuracion;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.espinozallontop.inventario.modelos.Tienda;
import com.espinozallontop.inventario.repositorios.TiendaRepositorio;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class CargarTienda {
	
	@Bean
	CommandLineRunner crearTiendas(TiendaRepositorio tiendaRepositorio) {
		return args -> {
			log.info("Creando Tienda 1 :" + tiendaRepositorio.save(
					Tienda.
						builder().
						codigo("T1").
						nombre("Tienda 1").
						id(1).
						build()
					)
			);
			log.info("Creando Tienda 2 :" + tiendaRepositorio.save(
					Tienda.
						builder().
						codigo("T2").
						nombre("Tienda 2").
						id(2).
						build()
					)
			);
			log.info("Creando Tienda 3 :" + tiendaRepositorio.save(
					Tienda.
						builder().
						codigo("T3").
						nombre("Tienda 3").
						id(3).
						build()
					)
			);
			log.info("Creando Tienda 4 :" + tiendaRepositorio.save(
					Tienda.
						builder().
						codigo("T4").
						nombre("Tienda 4").
						id(4).
						build()
					)
			);
		};
	}
	
}
