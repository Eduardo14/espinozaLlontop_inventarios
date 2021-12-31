package com.espinozallontop.inventario.controladores;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.espinozallontop.inventario.excepciones.ProductoNoEncontradoExcepcion;
import com.espinozallontop.inventario.excepciones.ProductoStockMenorCeroExcepcion;

@ControllerAdvice
public class ProductoExcepcionControlador {

	@ResponseBody
	@ExceptionHandler(ProductoNoEncontradoExcepcion.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	String productoNoEncontradoManejador(ProductoNoEncontradoExcepcion ex) {
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(ProductoStockMenorCeroExcepcion.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	String productoStockMenorCeroManejador(ProductoStockMenorCeroExcepcion ex) {
		return ex.getMessage();
	}
	
}
